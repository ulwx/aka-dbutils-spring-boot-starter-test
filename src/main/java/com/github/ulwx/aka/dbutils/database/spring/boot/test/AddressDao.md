getListMd
===
select * from address

updateMd0
===
UPDATE
`address`
SET
`name` = CONCAT('',now())
WHERE  `address_id` = 0

updateMd1
===
UPDATE
`address`
SET
`name` = CONCAT('',now())
WHERE  `address_id` = 1