package com.briup.restaurant.mapper.ex;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.ex.EndWait;

public interface WaitingEXMapper {
    int insertAndGetId(Waiting waiting);
    int countWait();
    EndWait selectEnd(int seat);
    void updateEnd(int id);
    void updateWait(int waitTable);
}
