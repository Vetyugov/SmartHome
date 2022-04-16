package Deviсes;

import lombok.extern.slf4j.Slf4j;
import publicServices.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DeviceMapper {

    private static DeviceMapper instance;

    private DeviceMapper() {
    }

    public static DeviceMapper getInstance() {
        DeviceMapper localInstance = instance;
        if (localInstance == null) {
            synchronized (DeviceMapper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    return new DeviceMapper();
                }
            }
        }
        return instance;
    }


    private final Connection connection = ConnectionService.getInstance().getConnection();
    private final HashMap<Integer, AbstractDevice> deviceRepo = new HashMap<>();

    /**
     * Метод ищет в памяти загруженный device.
     * Если находит -> обновляет поля,
     * если не находит -> создаёт новый объект с данными из БД
     * если объекта с таким id нет в БД, удаляем из памяти (вдруг его кто-то другой удалил в БД)
     * @param deviceId
     * @return
     * @throws SQLException
     */
    public AbstractDevice findById(int deviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Devices WHERE deviceId = ?");
        statement.setInt(1, deviceId);
        AbstractDevice device = null;
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                device = deviceRepo.get(deviceId);
                switch (resultSet.getString("deviceType")) {
                    case DeviceTypes.AIR_CONDITIONER -> {
                        if (device == null)
                            device = new AirСonditionerDeviсe(
                                    resultSet.getInt("deviceId"),
                                    resultSet.getString("deviceStatus")
                            );
                        ((AirСonditionerDeviсe)device).setSpeed(resultSet.getFloat("deviceSpeed"));
                    }
                    case DeviceTypes.TV -> {
                        if (device == null)
                            device = new TVDevice(
                                    resultSet.getInt("deviceId"),
                                    resultSet.getString("deviceStatus")
                            );
                        ((TVDevice)device).setVolume(resultSet.getFloat("deviceVolume"));
                    }
                }
                deviceRepo.put(deviceId, device);
            } else {
                deviceRepo.remove(deviceId);
            }
        } catch (SQLException e) {
            log.error("Error, when try to find device #"+ deviceId, e );
        }
        return device;
    }

    public void insert(AbstractDevice device) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Devices (`deviceType`,`deviceStatus`, `deviceSpeed`, `deviceVolume` ) VALUES (?,?,?,?)");
        String deviceType = null;
        String deviceStatus = device.getCurrentStatus();
        Float deviceSpeed = null;
        Float deviceVolume = null;
        if (device instanceof AirСonditionerDeviсe) {
            deviceType = DeviceTypes.AIR_CONDITIONER;
            deviceSpeed = ((AirСonditionerDeviсe) device).getSpeed();
        } else if (device instanceof TVDevice) {
            deviceType = DeviceTypes.TV;
            deviceVolume = ((TVDevice) device).getVolume();
        }

        if (deviceType == null) {
            statement.setNull(1, 0);
        } else {
            statement.setString(1, deviceType);
        }
        statement.setString(2, deviceStatus);
        if (deviceSpeed == null) {
            statement.setNull(3, 0);
        } else {
            statement.setFloat(3, deviceSpeed);
        }
        if (deviceVolume == null) {
            statement.setNull(4, 0);
        } else {
            statement.setFloat(4, deviceVolume);
        }
        statement.execute();
    }

    public void update(AbstractDevice device) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "Update INTO Devices SET `deviceType` = ? ,`deviceStatus` = ? , `deviceSpeed` = ? , `deviceVolume` = ? WHERE deviceId = ?");
        String deviceType = null;
        String deviceStatus = device.getCurrentStatus();
        Float deviceSpeed = null;
        Float deviceVolume = null;
        if (device instanceof AirСonditionerDeviсe) {
            deviceType = DeviceTypes.AIR_CONDITIONER;
            deviceSpeed = ((AirСonditionerDeviсe) device).getSpeed();
        } else if (device instanceof TVDevice) {
            deviceType = DeviceTypes.TV;
            deviceVolume = ((TVDevice) device).getVolume();
        }

        if (deviceType == null) {
            statement.setNull(1, 0);
        } else {
            statement.setString(1, deviceType);
        }
        statement.setString(2, deviceStatus);
        if (deviceSpeed == null) {
            statement.setNull(3, 0);
        } else {
            statement.setFloat(3, deviceSpeed);
        }
        if (deviceVolume == null) {
            statement.setNull(4, 0);
        } else {
            statement.setFloat(4, deviceVolume);
        }

        statement.setInt(5, device.getId());
        statement.execute();
    }

    public void delete(AbstractDevice device) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Devices WHERE deviceId = ?");
            statement.setInt(1, device.getId());
            statement.execute();
            deviceRepo.remove(device.getId());
        }catch (SQLException e){
            log.error("Command DELETE for device #" + device.getId() + " failed") ;
        }
    }

}
