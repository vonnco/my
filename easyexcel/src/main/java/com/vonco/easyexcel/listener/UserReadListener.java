package com.vonco.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.vonco.easyexcel.dao.UserMapper;
import com.vonco.easyexcel.domain.User;
import com.vonco.easyexcel.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ke feng
 * @title: UserReadListener
 * @projectName my
 * @description: TODO
 * @date 2021/12/10 15:22
 */
public class UserReadListener implements ReadListener<User> {
    private UserMapper userMapper;

    public UserReadListener(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<User> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param user
     * @param context
     */
    @Override
    public void invoke(User user, AnalysisContext context) {
        cachedDataList.add(user);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        if (!cachedDataList.isEmpty()) {
            userMapper.insertBatch(cachedDataList);
        }
    }
}
