package com.nic.calculate.help;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Component(value = "F5Controller")
public class BaseHeartbreakController {

    @RequestMapping(name = "SystemSampleHeartbreak", method = {RequestMethod.POST,RequestMethod.GET},
            path = "/system/heartbreak")
    public BaseResponse getHeartBreak() {
        heartbreakToLoadSomething();

        return new BaseResponse(true);
    }

    private static List<SampleCaller> callers = new ArrayList<>();

    public static void registerCaller(SampleCaller caller) {
        callers.add(caller);
    }

    public void heartbreakToLoadSomething() {
        if (callers != null && callers.size() > 0) {
            callers.forEach(c -> c.accept());
        }
    }
}
