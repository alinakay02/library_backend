INSERT INTO role (id, role_name)
VALUES
    (nextval('SEQ_LIB_ROLE'), 'User'),
    (nextval('SEQ_LIB_ROLE'), 'Admin');

INSERT INTO event (id, date, title, photo, event)
VALUES
    (nextval('SEQ_LIB_EVENT'), '2024-06-19', 'Литературный вечер', 'photo1.jpg', 'Приглашаем всех на литературный вечер, посвященный произведениям Михаила Булгакова'),
    (nextval('SEQ_LIB_EVENT'), '2024-05-20', 'Книжная ярмарка', 'photo2.jpg', 'Большая книжная ярмарка с участием известных авторов');

INSERT INTO item (id, date, title, photo, news)
VALUES
    (nextval('SEQ_LIB_ITEM'), '2024-04-19', 'Новинка книжного магазина', 'photo3.jpg', 'Представляем вашему вниманию новый роман "Мастер и Маргарита" от М.Булгакова.'),
    (nextval('SEQ_LIB_ITEM'), '2024-04-20', 'Специальное предложение', 'photo4.jpg', 'Только сегодня и только у нас - скидка 20% на все произведения Анны Ахматовой.');

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

INSERT INTO book (id, title, year, publisher, pdf_path)
VALUES
    (nextval('SEQ_BOOK'), 'Мастер и Маргарита', '2010', 'Дрофа', 'test.pdf'),
    (nextval('SEQ_BOOK'), 'Поэмы', '2000', 'Дрофа', ''),
    (nextval('SEQ_BOOK'), 'Двенадцать', '2012', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Анна Ахматова. Стихотворения', '2000', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Евгений Онегин', '2020', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Мертвые души', '2009', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Борис Годунов', '2008', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Дубровский', '2000', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Война и мир', '2010', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Преступление и наказание', '2010', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Невские мелодии', '2007', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Герой нашего времени', '2016', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Рудин', '2019', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Дама с собачкой', '2018', 'Дрофа', 'uploads/конспект вебинаров.pdf'),
    (nextval('SEQ_BOOK'), 'Доктор Живаго', '2009', 'Дрофа', 'uploads/конспект вебинаров.pdf');

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

INSERT INTO lib_user (id, role_id, login, name, surname, patronymic, password, cardId)
VALUES
    (nextval('SEQ_LIB_USER'), 1, 'q', 'Иван', 'Петров', 'Алексеевич', 'password1', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'qq', 'Петр', 'Войченко', 'Алексеевич', 'password2', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 2, 'qqq', 'Админ', 'Админ', 'Алексеевич', 'adminpassword', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'qqqq', 'Сидор', 'Сидоров', 'Алексеевич', 'password3', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'w', 'Елена', 'Смирнова', 'Алексеевна', 'password4', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 2, 'ww', 'Модератор', 'Модератор', 'Алексеевич', 'adminpassword2', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'www', 'Анна', 'Кузнецова', 'Алексеевна', 'password5', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'wwww', 'Дмитрий', 'Попов', 'Алексеевич', 'password6', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'e', 'Светлана', 'Морозова', 'Алексеевна', 'password7', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'ee', 'Алексей', 'Новиков', 'Алексеевич', 'password8', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'eee', 'Мария', 'Павлова', 'Алексеевна', 'password9', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'eeee', 'Артем', 'Волков', 'Алексеевич', 'password10', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'r', 'Татьяна', 'Соловьева', 'Алексеевна', 'password11', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 2, 'rr', 'Суперадмин', 'Суперадмин', 'Суперадмин','superadminpassword', 'AHA552602345'),
    (nextval('SEQ_LIB_USER'), 1, 'rrr', 'Ольга', 'Иванова', 'Алексеевна', 'password12', 'AHA552602345');

INSERT INTO order_book (date, state, book_id, id, user_id)
VALUES
    ('2024-04-19', true, 1, 1, 1),
    ('2023-04-19', null, 2, 2, 2),
    ('2022-04-19', true, 3, 3, 3),
    ('2021-04-19', null, 4, 4, 4),
    ('2020-04-19', true, 5, 5, 5),
    ('2024-04-19', false, 6, 6, 6),
    ('2023-04-19', true, 7, 7, 7),
    ('2022-04-19', false, 8, 8, 8),
    ('2020-04-19', true, 9, 9, 9),
    ('2024-04-19', null, 10, 10, 10),
    ('2023-04-19', true, 11, 11, 11),
    ('2024-04-19', false, 12, 12, 12),
    ('2022-04-19', true, 13, 13, 13),
    ('2021-04-19', false, 14, 14, 14),
    ('2020-04-19', true, 15, 15, 15);