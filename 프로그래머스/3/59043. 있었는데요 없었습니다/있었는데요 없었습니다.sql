-- 코드를 입력하세요
-- SELECT ain.DATETIME "보호 시작일", aout.DATETIME "입양일"
SELECT ain.ANIMAL_ID, aout.NAME
FROM ANIMAL_INS ain, ANIMAL_OUTS aout
WHERE ain.ANIMAL_ID = aout.ANIMAL_ID
and ain.DATETIME > aout.DATETIME
order by ain.DATETIME
