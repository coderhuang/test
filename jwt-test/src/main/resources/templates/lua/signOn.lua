
local setReturn = redis.call("HMSET", KEYS[1], ARGV[1], ARGV[2], ARGV[3], ARGV[4])

local successFlag = 0
if setReturn == "OK" then
	local expiration = tonumber(ARGV[5])
	successFlag = redis.call("EXPIRE", KEYS[1], expiration)
end

return successFlag
