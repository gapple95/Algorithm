-- 코드를 입력하세요
SELECT ain.NAME, ain.DATETIME
# SELECT *
FROM ANIMAL_OUTS aout
right join ANIMAL_INS ain
on ain.ANIMAL_ID = aout.ANIMAL_ID
where aout.ANIMAL_ID is null
order by ain.DATETIME
LIMIT 3