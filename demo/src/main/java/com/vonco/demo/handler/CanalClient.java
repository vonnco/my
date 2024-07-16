package com.vonco.demo.handler;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.vonco.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CanalClient {

    @Value("${canal.server.host}")
    private String canalHost;

    @Value("${canal.server.port}")
    private int canalPort;

    @Value("${canal.destination}")
    private String canalDestination;

    public void start() {
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress(canalHost, canalPort),
                canalDestination,
                "",
                ""
        );

        connector.connect();
        connector.subscribe(".*\\..*");

        while (true) {
            Message message = connector.getWithoutAck(100); // 获取指定数量的数据变更，没有确认ACK
            long batchId = message.getId();
            try {
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    Thread.sleep(1000);
                } else {
                    // 处理数据变更
                    handle(message.getEntries());
                }
                connector.ack(batchId); // 提交确认ACK
            } catch (Exception e) {
                connector.rollback(batchId); // 处理失败，回滚数据
            }
        }
    }

    // 处理接收到的数据变更
    private void handle(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                CanalEntry.RowChange rowChange;
                try {
                    rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("解析数据失败！", e);
                }

                for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                    if (rowChange.getEventType() == CanalEntry.EventType.INSERT) {
                        // 处理新增数据
                        handleInsert(rowData.getAfterColumnsList());
                    } else if (rowChange.getEventType() == CanalEntry.EventType.UPDATE) {
                        // 处理修改数据
                        System.out.println("修改前数据：" + rowData.getBeforeColumnsList());
                        System.out.println("修改后数据：" + rowData.getAfterColumnsList());
                    } else if (rowChange.getEventType() == CanalEntry.EventType.DELETE) {
                        // 处理删除数据
                        System.out.println("删除数据：" + rowData.getBeforeColumnsList());
                    }
                }
            }
        }
    }

    // 处理新增数据
    private void handleInsert(List<CanalEntry.Column> afterColumns) {
        User user = new User();
        for (CanalEntry.Column column : afterColumns) {
            String columnName = column.getName();
            String columnValue = column.getValue();
            // 根据列名进行映射，假设列名与 User 类的字段名相同
            switch (columnName) {
                case "id":
                    user.setId(Integer.valueOf(columnValue));
                    break;
                case "name":
                    user.setName(columnValue);
                    break;
                case "sex":
                    user.setSex(Integer.valueOf(columnValue));
                    break;
                case "age":
                    user.setAge(Integer.valueOf(columnValue));
                    break;
                case "birthday":
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthday = null;
                    try {
                        birthday = format.parse(columnValue);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    user.setBirthday(birthday);
                    break;
                case "address":
                    user.setAddress(columnValue);
                    break;
                // 可根据业务需要添加其他列映射
                // ...
            }
        }
        System.out.println("新增数据：" + user);
    }
}
