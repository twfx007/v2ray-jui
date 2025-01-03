package j2ray.command.support;

import j2ray.support.error.J2RayException;

public interface ICommand {
    void execute(String[] args) throws J2RayException;
    String commandKey();
}
