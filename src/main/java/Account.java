public class Account {
    private String id;
    private Long money;
    private Long point;

    public Account(String id, Long money, Long point) {
        this.id = id;
        this.money = money;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }
}
