-- 코드를 입력하세요
SELECT bo.BOOK_ID, au.AUTHOR_NAME, DATE_format(bo.PUBLISHED_DATE, "%Y-%m-%d")
FROM BOOK bo, AUTHOR au 
where bo.AUTHOR_ID = au.AUTHOR_ID AND bo.CATEGORY LIKE "경제"
order by PUBLISHED_DATE;