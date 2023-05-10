import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import random
import base64
import pymysql.cursors
import sys
import time
import os

plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号

def mainxh1():
    zuizhong = []
    for i in range(len(dtp)):
        fit = 1000000  # 设定初始适应值
        h = dth[i]           #水头
        p = dtp[i]/1000      #优化发电量
        q = dtq[i]         #优化发电流量

        if q > flowysu:
            q = flowysu

        for t in range(maxitem):
            while True:
                x1 = random.uniform(0, capdan[0]+1)
                x2 = random.uniform(0, min((p - x1) ,capdan[1])+1)
                x3 = random.uniform(0, min((p - x1 - x2), capdan[2])+1)
                x4 = random.uniform(0, min((p - x1 - x2 - x3), capdan[3])+1)
                x5 = random.uniform(0, min((p - x1 - x2 - x3 - x4), capdan[4])+1)
                x6 = p - x1 - x2 - x3 - x4 - x5
                if x6 > 0 and x6 < capdan[5]:
                    xall = [x1, x2, x3, x4, x5, x6]
                    break

            qal = fitness(xall, jizunum, h, zhendown, zhenup)

            if qal <= q:
                if qal <= fit:
                    fit = qal
                    xxall = [fit, x1, x2, x3, x4, x5, x6]
                else:
                    continue

        #print('第', i + 1, '个点最终组合为：', xxall)
        zuizhong.append(xxall)
    zzall = np.array(zuizhong)
    return zzall

def fitness(x, dim, whead, down, up):
    qall = []
    for i in range(dim):
        if down[i] <= x[i] <= up[i]:
            q = 1000000
        else:
            q = 0.598 * x[i] - 1.054 * whead + 205.013
        qall.append(q)
    qall1 = np.sum(qall)
    return qall1

def huitu1(ptotal, b):     # step3  左侧图展示
    with plt.style.context(('dark_background')):
        ax = plt.subplot(111)  # 机组总出力与外界负荷
        plt.plot(range(len(ptotal)), ptotal/1000, color='red', label='true value', linewidth=2)
        plt.bar(range(len(ptotal)), b[:, 1], label='#1号机组出力')
        plt.bar(range(len(ptotal)), b[:, 2], bottom=b[:, 1], label='#2号机组出力')
        plt.bar(range(len(ptotal)), b[:, 3], bottom=b[:, 1] + b[:, 2], label='#3号机组出力')
        plt.bar(range(len(ptotal)), b[:, 4], bottom=b[:, 1] + b[:, 2] + b[:, 3], label='#4号机组出力')
        plt.bar(range(len(ptotal)), b[:, 5], bottom=b[:, 1] + b[:, 2] + b[:, 3] + b[:, 4], label='#5号机组出力')
        plt.bar(range(len(ptotal)), b[:, 6], bottom=b[:, 1] + b[:, 2] + b[:, 3] + b[:, 4] + b[:, 5], label='#6号机组出力')
        ax.set_xlabel('时段/t')
        ax.set_ylabel('机组出力')
        ax.set_title('电站机组日内负荷分配图')
        plt.savefig('D:\\fuzaifenpei_resources\电站机组日内负荷分配图.jpg')

def opcl(ab):
    a = 0
    for co in range(1,7):
        for j in range(1,len(ab)):
            if ab[j-1,co]<180 and ab[j,co]>180:
                a = a + 1
            elif ab[j-1,co]<180 and ab[j,co]>400:
                a = a + 2
            elif ab[j-1,co]>180 and ab[j-1,co]<400 and ab[j,co]>400:
                a = a + 1
            elif ab[j-1,co]>400 and  ab[j,co]>180 and ab[j,co]<400:
                a = a + 1
            elif ab[j-1,co]>400 and ab[j,co]<180:
                a = a + 2
            elif ab[j-1,co]>180 and ab[j-1,co]<400 and ab[j,co]<180:
                a = a + 1
    return a

def act(state):
    """eps-greedy policy
    Return:
        action (tensor): action to execute
    """
    if np.random.rand() < epsilon:
        # explore - do random action
        index = random.randint(0,250)
        choose_action = action[index,:]
        while round(sum(choose_action)) > state:
            index = random.randint(0, 250)
            choose_action = action[index,:]
        return choose_action,index

    # exploit
    q_values = q[int(state),:]
    # select the action with max Q-value
    action_index = np.argmax(q_values)
    return action[action_index,:],action_index

def update_epsilon(e):
    if e > epsilon_min:
        e *= epsilon_decay
    return e

def inds(a, func):

    return [i for (i, val) in enumerate(a) if func(val)]

def huitu2(bene):
    with plt.style.context(('dark_background')):
        ax = plt.subplot(111)  # 机组总出力与外界负荷
        plt.plot(range(len(bene)), bene, color='red', linewidth=2)
        ax.set_xlabel('迭代次数/次')
        ax.set_ylabel('平均奖励/×3600立方米')
        ax.set_title('平均奖励变化曲线')
        plt.savefig('D:\\fuzaifenpei_resources\平均奖励变化曲线.jpg')

if __name__ == '__main__':

    start_time = time.time()  # 程序开始时间

    turbine = sys.argv[1]  # 电站名称
    starttime = sys.argv[2]  # 起始时间
    endtime = sys.argv[3]  # 终止时间
    jizunum = int(sys.argv[4])  # 机组台数        '6'
    capall = float(sys.argv[5])  # 总装机容量      '3300'
    capdan = sys.argv[6]  # 单机容量        '550,551,552,553,554,555'
    capdan = capdan.split(',')
    capdan = [float(x) for x in capdan]
    zhenup = sys.argv[7]  # 振动区上限      '400,401,402,403,404,405'
    zhenup = zhenup.split(',')
    zhenup = [float(x) for x in zhenup]
    zhendown = sys.argv[8]  # 振动区下限      '180,181,182,183,184,185'
    zhendown = zhendown.split(',')
    zhendown = [float(x) for x in zhendown]
    flowysd = float(sys.argv[9])  # 发电流量约束下限 ‘0’
    flowysu = float(sys.argv[10])  # 发电流量约束上限 '2226'
    ckfysd = float(sys.argv[11])  # 出库流量约束下限 '0'
    ckfysu = float(sys.argv[12])  # 出库流量约束上限 '19700'
    kurongd = float(sys.argv[13])  # 库容约束下限    '24.2'
    kurongu = float(sys.argv[14])  # 库容约束上限    '57.9'
    maxitem = int(sys.argv[15])  # 迭代次数        '100'
    calway = sys.argv[16]        # 方法名称      '动态规划计算负荷分配'

    conn = pymysql.connect(host='localhost', port=3306, user='root', password='123456', database='shuidianzhan',
                           charset='utf8')
    #连接数据库
    cursor = conn.cursor()
    ###----------------------------------------------------------------------------------------------------------------------------
    if calway == '动态规划计算负荷分配':

        sql = '''select * from nr_optimized_data where time between '{}%' and '{}%' '''.format(starttime,endtime)

        df = pd.read_sql(sql, conn)
        zval = df.values
        #扩展4份
        pd2 = []
        for i in range(len(zval)):
            x = 0
            while x < 4:
                a = zval[i, :]
                pd2.append(a)
                x = x + 1
        dataall = np.array(pd2)

        dtp = dataall[:, 3]  # 优化发电量  实际发电量是 10
        dtp2 = dataall[:, 10]
        dth = dataall[:, 8]  # 优化水头
        dtq = dataall[:, 7]  # 优化流量

        zzall = mainxh1()
        huitu1(dtp, zzall)
        chuan = opcl(zzall)
        ab = zzall.shape[0]

        val = []
        for i in range(len(dtp)):
            value = (dtp[i] - dtp2[i]) / dtp2[i] * 100
            val.append(value)
        valall = sum(val) / len(dtp)
        valall = ('%.3f' % valall)

        with open('D:\\fuzaifenpei_resources\电站机组日内负荷分配图.jpg','rb') as f:
            img = base64.b64encode(f.read())
        img = str(img)
        img = str('data:image/jpg;base64,') + img[2:-1]

        cursor.execute(
            'update nrfigs set figs = %s where id = %s', (chuan, 222))
        cursor.execute(
            'update nrfigs set figs = %s where id = %s', (valall, 333))

        # 保存数据
        cursor.execute('TRUNCATE TABLE nrdata')
        for i in range(0, ab):
            cursor.execute(
                'replace into nrdata (id, fitness, jizu1, jizu2, jizu3, jizu4, jizu5, jizu6) values(%s, %s, %s, %s, %s, %s, %s, %s)',
                (i + 1, zzall[i, 0], zzall[i, 1], zzall[i, 2], zzall[i, 3], zzall[i, 4], zzall[i, 5], zzall[i, 6]))

    elif calway == '深度强化学习计算偏差量':

        sql1 = '''select * from qlearning1'''
        sql2 = '''select * from qlearning2'''

        df1 = pd.read_sql(sql1, conn)
        A = df1.values
        action = np.array(A)  # 动作集

        df2 = pd.read_sql(sql2, conn)
        X = df2.values
        reward = np.array(X)  # 奖励集
        reward = reward.reshape(6, 251)

        q = np.zeros([6, 251], dtype=np.float32)

        gamma = 0.9  # 奖励递减值
        alpha = 1  # 学习率
        episodes = 5000

        epsilon = 1  # 贪婪率
        epsilon_min = 0.1
        epsilon_decay = epsilon_min / epsilon
        epsilon_decay = epsilon_decay ** (1. / float(episodes))

        state_num = 6
        action_num = 251
        final_state = 0

        zuizhong2 = []
        benefit = []
        for i in range(episodes):
            # randomly choose a state
            current_state = random.randint(0, state_num - 1)
            if current_state > 0:
                # randomly choose an action from current state
                chosen_action, chosen_action_index = act(current_state)
                # take action, observe reward and next state
                r = reward[int(current_state), chosen_action_index]
                next_state = current_state - round(sum(chosen_action))
                # update Q-table
                next_possible_action_list = inds(reward[int(next_state), :], lambda x: x > -100)
                maxQ = max(q[int(next_state), next_possible_action_list])
                q[int(current_state), chosen_action_index] = q[int(current_state), chosen_action_index] + alpha * (
                        r + gamma * maxQ - q[int(current_state), chosen_action_index])
                # q[int(current_state),chosen_action_index] = alpha*(r+gamma*maxQ)
                current_state = next_state
            else:
                q[int(current_state), 250] = 1
            epsilon = update_epsilon(epsilon)
            benefit.append(np.mean(q))
        ##决策
        for t in range(96):
            stai = random.randint(1, 5)
            State = np.array([stai])
            for i in range(1):
                currentstate = State[i]
                currenchu = currentstate
                while currentstate != final_state:
                    index = np.argmax(q[int(currentstate), :])
                    chosenaction = action[index, :]
                    nextstate = currentstate - round(sum(chosenaction))
                    currentstate = nextstate
                #print('第', t + 1, '个点的初始偏差量为：', currenchu, '，选择机组的调整量为：', chosenaction, '出力再调整量为：', nextstate)
                zzstai = np.append(chosenaction, [currenchu, nextstate])
                zuizhong2.append(zzstai)

        huitu2(benefit)

        with open('D:\\fuzaifenpei_resources\\平均奖励变化曲线.jpg','rb') as f:
            img = base64.b64encode(f.read())
        img = str(img)
        img = str('data:image/jpg;base64,') + img[2:-1]

        zuizhong2 = np.array(zuizhong2)
        ab = zuizhong2.shape[0]
        conn = pymysql.connect(host='localhost', port=3306, user='root', password='123456', database='shuidianzhan',
                               charset='utf8')
        cursor = conn.cursor()
        cursor.execute('TRUNCATE TABLE nrdata2')
        for i in range(0, ab):
            cursor.execute(
                'replace into nrdata2 (id, chushi, jizu1, jizu2, jizu3, jizu4, pagain) values(%s, %s, %s, %s, %s, %s, %s)',
                (i + 1, zuizhong2[i, 4], zuizhong2[i, 0], zuizhong2[i, 1], zuizhong2[i, 2], zuizhong2[i, 3],
                 zuizhong2[i, 5]))

    ###-------------------------------------------------------------------------------------------------------------------------------------
    #保存图片
    cursor.execute(
        'update nrfigs set figs = %s where id = %s', (img, 111))

    conn.commit()
    cursor.close()
    # 关闭数据库连接
    conn.close()

    end_time = time.time()  # 程序结束时间
    run_time = end_time - start_time
    print(run_time)