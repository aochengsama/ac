import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import random
import base64
import sys

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
                    xxall = [i+1, fit, x1, x2, x3, x4, x5, x6]
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
        #plt.show()

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
    #print('穿越振动区次数为：',a)
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
        #plt.show()

if __name__ == '__main__':

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
    calway = sys.argv[16]  # 方法名称      '动态规划计算负荷分配'

    ###----------------------------------------------------------------------------------------------------------------------------
    if calway == '动态规划计算负荷分配':

        df = pd.read_excel('D:\\fuzaifenpei_resources\\读取数据.xlsx')
        dtp = df.优化发电
        dtp2 = df.实际发电
        dth = df.优化发电水头
        dtq = df.优化发电流量

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

        # 保存数据
        column2 = ['id', 'fitness', 'jizu1', 'jizu2', 'jizu3', 'jizu4', 'jizu5', 'jizu6']
        nrdata = pd.DataFrame(columns=column2, data=zzall)  # 将数据放进表格
        df2 = nrdata.to_excel('D:\\fuzaifenpei_resources\\nrdata.xlsx', index=False)  # 数据存入

    elif calway == '深度强化学习计算偏差量':

        X = pd.read_excel('D:\\fuzaifenpei_resources\\qianghuadata.xlsx', sheet_name=1, header=None)
        reward = np.array(X)  # 奖励集

        A = pd.read_excel('D:\\fuzaifenpei_resources\\qianghuadata.xlsx', sheet_name=0, header=None)
        action = np.array(A)  # 动作集

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
                zzstai = [t+1, currenchu, chosenaction[0], chosenaction[1], chosenaction[2], chosenaction[3], nextstate]
                #zzstai = np.append(chosenaction, [currenchu, nextstate])
                zuizhong2.append(zzstai)

        huitu2(benefit)

        with open('D:\\fuzaifenpei_resources\\平均奖励变化曲线.jpg','rb') as f:
            img = base64.b64encode(f.read())
        img = str(img)
        img = str('data:image/jpg;base64,') + img[2:-1]
        valall = 0
        chuan = 0

        zuizhong2 = np.array(zuizhong2)

        column3 = ['id','chushi', 'jizu1', 'jizu2', 'jizu3', 'jizu4', 'pagain']
        nrdata2 = pd.DataFrame(columns=column3, data=zuizhong2)  # 将数据放进表格
        df3 = nrdata2.to_excel('D:\\fuzaifenpei_resources\\nrdata2.xlsx', index=False)  # 数据存入csv,存储位置及文件名称

    ###-------------------------------------------------------------------------------------------------------------------------------------
    #保存图片
    nrfig = [[img, valall, chuan]]

    column1 = ['figs', 'fadian', 'chuanyue']  # 列表头名称
    nrfigs = pd.DataFrame(columns=column1, data=nrfig)  # 将数据放进表格
    df1 = nrfigs.to_excel('D:\\fuzaifenpei_resources\\nrfigs.xlsx', index=False)  # 数据存入csv,存储位置及文件名称


