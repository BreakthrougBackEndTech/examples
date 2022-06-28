import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

/**
 * @author luffy
 * @date 2022/6/26
 */
public class InfluxDB2Example {
    private static char[] token = "tSa5AqOfXPTPps9ELIXSQKaJUfyMNmXTn-xUu1t4KsS1zKv3X3dYCdfUmIt4AOTDfY1c0dXTQNzTNK4o5z7RJg==".toCharArray();
    private static String org = "cmict";
    private static String bucket = "luffy";

    public static void main(final String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

        //
        // Write data
        //
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

        //
        // Write by Data Point
        //
//        Point point = Point.measurement("temperature")
//                .addTag("location", "west")
//                .addField("value", 55D)
//                .time(Instant.now().toEpochMilli(), WritePrecision.MS);
//
//        writeApi.writePoint(point);

        //
        // Write by LineProtocol
        //
//        writeApi.writeRecord(WritePrecision.NS, "temperature,location=north value=60.0");

        //
        // Write by POJO
        //
//        Temperature temperature = new Temperature();
//        temperature.location = "south";
//        temperature.value = 62D;
//        temperature.time = Instant.now();
//
//        writeApi.writeMeasurement( WritePrecision.NS, temperature);

        Zwfw zwfw = new Zwfw();
        zwfw.areaCode="6200";
        zwfw.shiXian=100000D;
        zwfw.banJian=100D;

        zwfw.time=Instant.now();

        writeApi.writeMeasurement( WritePrecision.NS, zwfw);


        //
        // Query data
        //
        String flux = "from(bucket:\"luffy\") |> range(start: 0)  |> filter(fn: (r) => r[\"_measurement\"] == \"zwfw\")";

        QueryApi queryApi = influxDBClient.getQueryApi();

        List<FluxTable> tables = queryApi.query(flux);
        for (FluxTable fluxTable : tables) {
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord fluxRecord : records) {
                System.out.println(fluxRecord.getTime() + ": " + fluxRecord.getValueByKey("_field")+":"+ fluxRecord.getValueByKey("_value"));
            }
        }

        influxDBClient.close();
    }

    @Measurement(name = "temperature")
    private static class Temperature {

        @Column(tag = true)
        String location;

        @Column
        Double value;

        @Column(timestamp = true)
        Instant time;
    }

    @Measurement(name = "zwfw")
    private static class Zwfw {

        @Column(tag = true)
        String areaCode;  //6200

        //时间维度   截至昨日数据

        //首页14个

        @Column
        Double shiXian;

        @Column
        Double banJian;

        @Column(timestamp = true)
        Instant time;
    }
}
