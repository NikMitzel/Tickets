package com.example.Ticketsystem.APIs;

import java.util.Date;

public class AddTicket {
    private Date start;
    private Date end;
    private int freqency;

    public AddTicket(Date start, Date end, int freqency) {
        this.start = start;
        this.end = end;
        this.freqency = freqency;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public int getFreqency() {
        return freqency;
    }
}
