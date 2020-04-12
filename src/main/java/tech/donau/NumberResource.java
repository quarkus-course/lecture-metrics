package tech.donau;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Random;

@Path("/number")
public class NumberResource {

    int lastNumber = 0;

    @GET
    @Timed(description = "How long random number returns", unit = MetricUnits.MILLISECONDS, name = "execTime")
    @Counted(description = "How many times random number returned", name = "execCount")
    public String getRandomNumber() throws InterruptedException {
        final Random random = new Random();
        final int randomInteger = random.nextInt(100);
        Thread.sleep(randomInteger * 100);
        lastNumber = randomInteger;
        return randomInteger + "";
    }

    @Gauge(name = "maxNumber", unit = MetricUnits.NONE)
    public int getMaxNumber() {
        return lastNumber;
    }
}
