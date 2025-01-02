package bb.j2ray.config;

public enum DomainStrategy {
    AsIs("AsIs"),
    IPIfNonMatch("IPIfNonMatch"),
    IPOnDemand("IPOnDemand")
    ;
    public final String strategy;

    DomainStrategy(String strategy) {
        this.strategy = strategy;
    }
}
