/**
 * Creates pre-filled demo account
 */

print('dump start');

db.accounts.update(
    { "_id": "demo" },
    {
        "_id": "demo",
        "lastSeen": new Date(),
        "note": "demo note",
        "data": []
    },
    { upsert: true }
);

print('dump complete');