package j2ray.support.config;

public enum DomainMatcher {
    linear("linear"),
    mph("mph")
    ;
    public final String matcher;

    DomainMatcher(String matcher) {
        this.matcher = matcher;
    }
}
