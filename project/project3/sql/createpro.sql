DROP PROCEDURE IF EXISTS add_movie;

DELIMITER //
CREATE PROCEDURE add_movie
(IN title varchar(100),
IN year int(11),
IN director varchar(100),
IN star_name VARCHAR(100),
IN star_birthYear int(11),
IN genre VARCHAR(200),
OUT responseMsg VARCHAR(200)
)
BEGIN
	DECLARE Exist INT DEFAULT 0;
	DECLARE genre_id INT DEFAULT 0 ;
	DECLARE max_movie_id varchar(10) default null;
    DECLARE max_star_id varchar(10) default null;
    DECLARE movie_id varchar(10) default null;
    DECLARE star_id varchar(10) default null;
	
    SELECT movies.id
	INTO Exist
	FROM movies 
	WHERE  movies.title = title AND movies.year = year AND movies.director = director;

	proc_label:BEGIN
		IF Exist > 0 THEN
			SET @responseMsg = 'Movie not added, already exists.';
		ELSE
			SET @responseMsg = 'Movie added. ';
			set max_movie_id = (select max(id) from movies);
		    set max_movie_id = cast(SUBSTRING(max_movie_id,3) as unsigned);
		    set max_movie_id = max_movie_id + 1;
            set movie_id = concat("tt0", cast(max_movie_id as char));
			INSERT INTO movies (id, title, year, director) VALUES (movie_id, title, year, director);
		END IF;

		SELECT stars.id
		INTO Exist 
		FROM stars 
		WHERE  stars.name = star_name AND stars.birthYear = star_birthYear;

		IF Exist = 0 THEN
		    set max_star_id = (select max(id) from stars);
			set max_star_id = cast(SUBSTRING(max_star_id,3) as unsigned);
			set max_star_id = max_star_id + 1;
			set star_id = concat("nm", cast(max_star_id as char));
			INSERT INTO stars (id, name, birthYear) VALUES (star_id, star_name, star_birthYear);
			SET @responseMsg = concat(@responseMsg , 'Star was not found and was created. ') ;
		END IF;
        
        IF genre != "" THEN
			SELECT id, count(*) 
			INTO genre_id, Exist 
			FROM genres 
			WHERE genres.name = genre;
		END IF;

		IF Exist = 0  AND genre != "" THEN
			INSERT INTO genres (name) VALUES (genre);
			SET genre_id = LAST_INSERT_ID();
			SET @responseMsg = concat(@responseMsg , 'Genre was not found and was created. ') ;
		END IF;
        
        If genre_id != 0 THEN
			INSERT INTO genres_in_movies (genreId, movieId) VALUES (genre_id, movie_id);
        END IF;
        
        IF star_id != "" THEN
			INSERT INTO stars_in_movies (starId, movieId) VALUES (star_id, movie_id);
        END IF;
	END;
    SET responseMsg = @responseMsg;
END //
DELIMITER ;