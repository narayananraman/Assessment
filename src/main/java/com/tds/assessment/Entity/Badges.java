package com.tds.assessment.Entity;

public class Badges {

    private int badge_number;
    private String badgeStatus;
    private String badgeExpiryDate;

    public int getBadge_number() {
        return badge_number;
    }

    public void setBadge_number(int badge_number) {
        this.badge_number = badge_number;
    }

    public String getBadgeStatus() {
        return badgeStatus;
    }

    public void setBadgeStatus(String badgeStatus) {
        this.badgeStatus = badgeStatus;
    }

    public String getBadgeExpiryDate() {
        return badgeExpiryDate;
    }

    public void setBadgeExpiryDate(String badgeExpiryDate) {
        this.badgeExpiryDate = badgeExpiryDate;
    }
}
