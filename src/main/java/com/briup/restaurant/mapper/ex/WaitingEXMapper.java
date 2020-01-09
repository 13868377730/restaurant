package com.briup.restaurant.mapper.ex;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.ex.EndWait;

public interface WaitingEXMapper {
    int insertAndGetId(Waiting waiting);
    int countWait(int seat);
    EndWait selectEnd(int seat);
    void updateEnd(int id);
    void updateWait(int seat);
    void updateCancelWait(int seat, int waitingTable);
}
