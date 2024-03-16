CREATE FUNCTION add_annual_flights()
    RETURNS VOID
    LANGUAGE plpgsql
AS
$$
DECLARE
    start_date DATE;
    end_date   DATE;
BEGIN
    start_date := '2024-01-01';
    end_date := '2024-12-31';

    WHILE
        start_date <= end_date
        LOOP
            INSERT INTO flights (designator_code, arrival_icao_code, cabins, call_sign, departure_date_time, departure_icao_code, duration_minutes)
            VALUES (gen_random_uuid(), 'LFPG', '{"ECONOMY"}', 123,
                    TO_TIMESTAMP(start_date || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'EPGD', 150),
                   (gen_random_uuid(), 'EPGD', '{"ECONOMY"}', 124,
                    TO_TIMESTAMP(start_date || ' 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'LFPG', 150),
                   (gen_random_uuid(), 'EGLL', '{"ECONOMY"}', 125,
                    TO_TIMESTAMP(start_date || ' 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'EPGD', 180),
                   (gen_random_uuid(), 'EPGD', '{"ECONOMY"}', 126,
                    TO_TIMESTAMP(start_date || ' 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'EGLL', 180),
                   (gen_random_uuid(), 'EKCH', '{"ECONOMY"}', 125,
                    TIMESTAMP(start_date || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'EPGD', 150),
                   (gen_random_uuid(), 'EPGD', '{"ECONOMY"}', 126,
                    TIMESTAMP(start_date || ' 09:50:00', 'YYYY-MM-DD HH24:MI:SS'), 'EKCH', 150),
                   (gen_random_uuid(), 'LIRF', '{"ECONOMY"}', 127,
                    TIMESTAMP(start_date || ' 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'EPGD', 160),
                   (gen_random_uuid(), 'EPGD', '{"ECONOMY"}', 128,
                    TIMESTAMP(start_date || ' 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'LIRF', 160),
                   (gen_random_uuid(), 'EDDF', '{"ECONOMY"}', 129,
                    TIMESTAMP(start_date || ' 12:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'EPGD', 170),
                   (gen_random_uuid(), 'EPGD', '{"ECONOMY"}', 130,
                    TIMESTAMP(start_date || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'EDDF', 170),
                   (gen_random_uuid(), 'EHAM', '{"ECONOMY"}', 131,
                    TIMESTAMP(start_date || ' 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'EPGD', 180),
                   (gen_random_uuid(), 'EPGD', '{"ECONOMY"}', 132,
                    TIMESTAMP(start_date || ' 17:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'EHAM', 180);
            start_date := start_date + INTERVAL '1 day';
        END LOOP;
END;
$$;

SELECT add_annual_flights();
