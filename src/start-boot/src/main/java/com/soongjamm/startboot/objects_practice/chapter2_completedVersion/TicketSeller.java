package com.soongjamm.startboot.objects_practice.chapter2_completedVersion;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

   public void sellTo(Audience audience) {
        ticketOffice.sellTicketTo(audience);
   }
}
