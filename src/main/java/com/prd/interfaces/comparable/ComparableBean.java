package com.prd.interfaces.comparable;

/**
 * 待排序对象
 */
public class ComparableBean implements Comparable<ComparableBean> {

    private String userName;

    public ComparableBean(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(ComparableBean bean) {
        if (userName.length()<bean.getUserName().length()) {
            return 1;
        } else if (userName.length()>bean.getUserName().length()) {
            return -1;
        } else {
            return 0;
        }
    }
}

