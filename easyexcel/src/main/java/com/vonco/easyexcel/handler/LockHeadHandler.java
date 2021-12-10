package com.vonco.easyexcel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

/**
 * @author ke feng
 * @title: LockHeadConverter
 * @projectName my
 * @description: 固定头策略
 * @date 2021/12/10 11:16
 */
public class LockHeadHandler implements SheetWriteHandler {
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        //冻结的列数、冻结的行数、首列序号、首行序号
        writeSheetHolder.getSheet().createFreezePane(0,1,0,1);
    }
}
