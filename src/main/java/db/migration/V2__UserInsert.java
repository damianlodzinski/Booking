package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Statement;
import java.util.Arrays;

public class V2__UserInsert extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        try (Statement update = context.getConnection().createStatement()) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader("/Users/Damian_1/IdeaProjects/Booking/userData.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Object[] values = Arrays.stream(line.split(","))
                            .map(value -> value.substring(1)).toArray();
                    if (!values[2].equals("password")) {
                        update.execute("insert into user (login, password, role) values ('" +
                                values[1] + "', '" +
                                values[2] + "', '" +
                                values[3].toString().substring(0, values[3].toString().length() - 1) + "')");
                    }
                }
            }
        }
    }
}
