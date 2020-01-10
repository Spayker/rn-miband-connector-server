/**
 * Creates pre-filled demo device
 */

print('dump start');

db.devices.update(
    {
        "deviceId": "343452345",
        "username": "user1",
        "date": new Date(),
        "hrData": "72"
    },
    { upsert: true }
);

print('dump complete');