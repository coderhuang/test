
local setReturn = redis.call("HMSET", KEYS[1], ARGV[1], ARGV[2], ARGV[3], ARGV[4])

local expiration = tonumber(ARGV[5])
local successFlag = 0
successFlag = redis.call("EXPIRE", KEYS[1], expiration)

expiration = tonumber(ARGV[7])
redis.call("SETEX", KEYS[2], expiration, ARGV[6])

return successFlag
