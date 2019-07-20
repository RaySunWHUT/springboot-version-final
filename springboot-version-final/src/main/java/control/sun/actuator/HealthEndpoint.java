package control.sun.actuator;//package season.spring.actuator;
//
//import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
//import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
//import org.springframework.boot.actuate.health.Health;
//
//@Endpoint(id = "health")
//public class HealthEndpoint {
//
//
//    @ReadOperation
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
//        return "DOWN";
//
//    }
//
//}
