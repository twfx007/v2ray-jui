package bb.j2ray.config;

public enum BalancerStrategy {
    random("random"),
    leastping("leastPing"),
    leastload("leastload")
    ;
    public final String strategy;

    BalancerStrategy(String strategy) {
        this.strategy = strategy;
    }
}
