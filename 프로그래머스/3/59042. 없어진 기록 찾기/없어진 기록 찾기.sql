-- 코드를 입력하세요
SELECT aout.ANIMAL_ID, aout.NAME
FROM ANIMAL_OUTS aout LEFT OUTER JOIN ANIMAL_INS ain
on aout.ANIMAL_ID = ain.ANIMAL_ID
where ain.ANIMAL_ID is null
