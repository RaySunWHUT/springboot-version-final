package control.sun.actuator;//package season.spring.actuator;
//
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.stereotype.Component;
//
///**
// * 自定义Health监控类
// */
//@Component
//public class MyHealthIndicator implements HealthIndicator {
//
//    @Override
//    public Health health() {
//
//        Long totalSpace = checkTotalSpace();
//        Long free = checkFree();
//        String status = checkStatus();
//        checkFree();
//
//        return new Health.Builder()
//                .up()
//                .withDetail("status", status)
//                .withDetail("total", totalSpace)
//                .withDetail("free", free)
//                .build();
//
//    }
//
//
//    private Long checkFree() {
//
//        // 结合真实项目获取相关参数
//        return 5000L;
//
//    }
//
//
//    private Long checkTotalSpace() {
//
//        // 结合真实项目获取相关参数
//        return 10000L;
//
//    }
//
//
//    private String checkStatus() {
//
//        // 结合真实项目获取相关参数
//        return "UP";
//
//    }
//
//}
