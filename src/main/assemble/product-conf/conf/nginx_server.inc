location / {
                client_max_body_size    100m;
                client_body_buffer_size 1024k;
                proxy_connect_timeout   30;
                proxy_pass    http://10237;
                proxy_redirect off;
                proxy_set_header Host $host;
}
