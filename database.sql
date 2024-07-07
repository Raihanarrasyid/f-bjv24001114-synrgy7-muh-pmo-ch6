CREATE TABLE
    users (
        id serial PRIMARY KEY,
        username text NOT NULL,
        email text NOT NULL,
        password text NOT NULL
    );

CREATE TABLE
    merchants (
        id serial PRIMARY KEY,
        name text NOT NULL,
        location text NOT NULL,
        open boolean NOT NULL
    );

CREATE TABLE
    orders (
        id serial PRIMARY KEY,
        time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        destination_address text NOT NULL,
        completed boolean NOT NULL DEFAULT FALSE,
        user_id integer REFERENCES users (id)
    );

CREATE TABLE
    products (
        id serial PRIMARY KEY,
        name text NOT NULL,
        price integer NOT NULL,
        merchant_id integer REFERENCES merchants (id)
    );

CREATE TABLE
    order_details (
        id serial PRIMARY KEY,
        order_id integer REFERENCES orders (id),
        product_id integer REFERENCES products (id),
        quantity integer NOT NULL
    );

CREATE TABLE
    oauth_user (
        id serial PRIMARY KEY,
        not_expired boolean,
        not_locked boolean,
        credential_not_expired boolean,
        enabled boolean,
        expired_verify_token timestamp,
        fullname text,
        is_blocked boolean,
        is_change boolean,
        limitotp integer,
        otp integer,
        otp_expired_date timestamp,
        password text,
        resend_otp_date timestamp,
        status text,
        username text,
        verify_token text
);

CREATE TABLE
    oauth_role (
        id serial PRIMARY KEY,
        name text,
        type text
);

CREATE TABLE
    oauth_user_role (
            user_id integer REFERENCES oauth_user (id),
            role_id integer REFERENCES  oauth_role (id)
);

CREATE TABLE
    oauth_role_path (
        id serial primary key,
        method text,
        name text,
        pattern text,
        role_id integer references oauth_role (id)
);

create table
    oauth_client (
        id serial primary key,
        access_token_expired integer,
        approved boolean,
        client_id text,
        client_secret text,
        grant_types text,
        redirect_uris text,
        refresh_token_expired integer,
        scopes text
);

create table
        oauth_client_role (
            client_id integer references oauth_client (id),
            role_id integer references oauth_role (id)
);


select * from users;

