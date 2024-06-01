INSERT INTO role (id, role_name)
VALUES
    (nextval('SEQ_LIB_ROLE'), 'User'),
    (nextval('SEQ_LIB_ROLE'), 'Admin');

INSERT INTO event (id, date, title, photo, event)
VALUES
    (nextval('SEQ_LIB_EVENT'), '2024-04-19 10:00:00', 'Литературный вечер', 'photo1.jpg', 'Приглашаем всех на литературный вечер, посвященный произведениям Михаила Булгакова'),
    (nextval('SEQ_LIB_EVENT'), '2024-04-20 14:00:00', 'Книжная ярмарка', 'photo2.jpg', 'Большая книжная ярмарка с участием известных авторов');

INSERT INTO item (id, date, title, photo, news)
VALUES
    (nextval('SEQ_LIB_ITEM'), '2024-04-19 12:00:00', 'Новинка книжного магазина', 'photo3.jpg', 'Представляем вашему вниманию новый роман "Мастер и Маргарита" от М.Булгакова.'),
    (nextval('SEQ_LIB_ITEM'), '2024-04-20 09:30:00', 'Специальное предложение', 'photo4.jpg', 'Только сегодня и только у нас - скидка 20% на все произведения Анны Ахматовой.');

INSERT INTO genre (id, genre_name)
VALUES
    (nextval('SEQ_GENRE'), 'Роман'),
    (nextval('SEQ_GENRE'), 'Поэзия'),
    (nextval('SEQ_GENRE'), 'Драма'),
    (nextval('SEQ_GENRE'), 'Проза'),
    (nextval('SEQ_GENRE'), 'Фантастика'),
    (nextval('SEQ_GENRE'), 'Философия'),
    (nextval('SEQ_GENRE'), 'История'),
    (nextval('SEQ_GENRE'), 'Художественная литература'),
    (nextval('SEQ_GENRE'), 'Классика'),
    (nextval('SEQ_GENRE'), 'Сказки'),
    (nextval('SEQ_GENRE'), 'Мистика'),
    (nextval('SEQ_GENRE'), 'Приключения'),
    (nextval('SEQ_GENRE'), 'Триллер'),
    (nextval('SEQ_GENRE'), 'Научная литература'),
    (nextval('SEQ_GENRE'), 'Биография'),
    (nextval('SEQ_GENRE'), 'Детектив'),
    (nextval('SEQ_GENRE'), 'Ужасы'),
    (nextval('SEQ_GENRE'), 'Путешествия'),
    (nextval('SEQ_GENRE'), 'Фэнтези'),
    (nextval('SEQ_GENRE'), 'Религия'),
    (nextval('SEQ_GENRE'), 'Юмор'),
    (nextval('SEQ_GENRE'), 'Политика'),
    (nextval('SEQ_GENRE'), 'Военная проза'),
    (nextval('SEQ_GENRE'), 'Экономика'),
    (nextval('SEQ_GENRE'), 'Детская литература'),
    (nextval('SEQ_GENRE'), 'Спорт'),
    (nextval('SEQ_GENRE'), 'Домашнее хозяйство'),
    (nextval('SEQ_GENRE'), 'Социальная литература'),
    (nextval('SEQ_GENRE'), 'Документальная литература'),
    (nextval('SEQ_GENRE'), 'Автобиография'),
    (nextval('SEQ_GENRE'), 'Научная фантастика'),
    (nextval('SEQ_GENRE'), 'Психология');

INSERT INTO author (id, name, patronymic, surname)
VALUES
    (nextval('SEQ_AUTHOR'), 'Михаил', 'Афанасьевич', 'Булгаков'),
    (nextval('SEQ_AUTHOR'), 'Анна', 'Андреевна', 'Ахматова'),
    (nextval('SEQ_AUTHOR'), 'Александр', 'Александрович', 'Блок'),
    (nextval('SEQ_AUTHOR'), 'Сергей', 'Александрович', 'Есенин'),
    (nextval('SEQ_AUTHOR'), 'Иван', 'Сергеевич', 'Тургенев'),
    (nextval('SEQ_AUTHOR'), 'Николай', 'Васильевич', 'Гоголь'),
    (nextval('SEQ_AUTHOR'), 'Фёдор', 'Иванович', 'Тютчев'),
    (nextval('SEQ_AUTHOR'), 'Александр', 'Сергеевич', 'Пушкин'),
    (nextval('SEQ_AUTHOR'), 'Лев', 'Николаевич', 'Толстой'),
    (nextval('SEQ_AUTHOR'), 'Фёдор', 'Михайлович', 'Достоевский'),
    (nextval('SEQ_AUTHOR'), 'Николай', 'Семенович', 'Лесков'),
    (nextval('SEQ_AUTHOR'), 'Михаил', 'Юрьевич', 'Лермонтов'),
    (nextval('SEQ_AUTHOR'), 'Иван', 'Александрович', 'Бунин'),
    (nextval('SEQ_AUTHOR'), 'Антон', 'Павлович', 'Чехов'),
    (nextval('SEQ_AUTHOR'), 'Борис', 'Леонидович', 'Пастернак');

INSERT INTO book (id, title)
VALUES
    (nextval('SEQ_BOOK'), 'Мастер и Маргарита'),
    (nextval('SEQ_BOOK'), 'Поэмы'),
    (nextval('SEQ_BOOK'), 'Двенадцать'),
    (nextval('SEQ_BOOK'), 'Анна Ахматова. Стихотворения'),
    (nextval('SEQ_BOOK'), 'Евгений Онегин'),
    (nextval('SEQ_BOOK'), 'Мертвые души'),
    (nextval('SEQ_BOOK'), 'Борис Годунов'),
    (nextval('SEQ_BOOK'), 'Дубровский'),
    (nextval('SEQ_BOOK'), 'Война и мир'),
    (nextval('SEQ_BOOK'), 'Преступление и наказание'),
    (nextval('SEQ_BOOK'), 'Невские мелодии'),
    (nextval('SEQ_BOOK'), 'Герой нашего времени'),
    (nextval('SEQ_BOOK'), 'Рудин'),
    (nextval('SEQ_BOOK'), 'Дама с собачкой'),
    (nextval('SEQ_BOOK'), 'Доктор Живаго');

INSERT INTO book_authors(author_id, book_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15);

INSERT INTO book_genres(book_id, genre_id)
VALUES
    (1, 9),
    (2, 2),
    (3, 11),
    (4, 2),
    (5, 9),
    (6, 8),
    (7, 3),
    (8, 12),
    (9, 7),
    (10, 13),
    (11, 15),
    (12, 11),
    (13, 9),
    (14, 14),
    (15, 8);

INSERT INTO lib_user (id, role_id, login, name, surname, password)
VALUES
    (nextval('SEQ_LIB_USER'), 1, 'q', 'Иван', 'Петров', 'password1'),
    (nextval('SEQ_LIB_USER'), 1, 'qq', 'Петр', 'Войченко', 'password2'),
    (nextval('SEQ_LIB_USER'), 2, 'qqq', 'Админ', 'Админ', 'adminpassword'),
    (nextval('SEQ_LIB_USER'), 1, 'qqqq', 'Сидор', 'Сидоров', 'password3'),
    (nextval('SEQ_LIB_USER'), 1, 'w', 'Елена', 'Смирнова', 'password4'),
    (nextval('SEQ_LIB_USER'), 2, 'ww', 'Модератор', 'Модератор', 'adminpassword2'),
    (nextval('SEQ_LIB_USER'), 1, 'www', 'Анна', 'Кузнецова', 'password5'),
    (nextval('SEQ_LIB_USER'), 1, 'wwww', 'Дмитрий', 'Попов', 'password6'),
    (nextval('SEQ_LIB_USER'), 1, 'e', 'Светлана', 'Морозова', 'password7'),
    (nextval('SEQ_LIB_USER'), 1, 'ee', 'Алексей', 'Новиков', 'password8'),
    (nextval('SEQ_LIB_USER'), 1, 'eee', 'Мария', 'Павлова', 'password9'),
    (nextval('SEQ_LIB_USER'), 1, 'eeee', 'Артем', 'Волков', 'password10'),
    (nextval('SEQ_LIB_USER'), 1, 'r', 'Татьяна', 'Соловьева', 'password11'),
    (nextval('SEQ_LIB_USER'), 2, 'rr', 'Суперадмин', 'Суперадмин', 'superadminpassword'),
    (nextval('SEQ_LIB_USER'), 1, 'rrr', 'Ольга', 'Иванова', 'password12');

INSERT INTO order_book (state, book_id, id, user_id)
VALUES
    (true, 1, 1, 1),
    (false, 2, 2, 2),
    (true, 3, 3, 3),
    (false, 4, 4, 4),
    (true, 5, 5, 5),
    (false, 6, 6, 6),
    (true, 7, 7, 7),
    (false, 8, 8, 8),
    (true, 9, 9, 9),
    (false, 10, 10, 10),
    (true, 11, 11, 11),
    (false, 12, 12, 12),
    (true, 13, 13, 13),
    (false, 14, 14, 14),
    (true, 15, 15, 15);