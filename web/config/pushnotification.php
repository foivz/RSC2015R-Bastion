<?php

return [

    'iOS'     => [
        'environment' => env('IOS_PUSH_ENV', 'development'),
        'certificate' => env('IOS_PUSH_CERT', __DIR__ . '/ios-push-notification-certificates/development/notification.pem'),
        'passPhrase'  => env('IOS_PUSH_PASSWORD', '123456'),
        'service'     => 'apns'
    ],

    'android' => [
        'environment' => env('ANDROID_PUSH_ENV', 'development'),
        'apiKey'      => env('ANDROID_PUSH_API_KEY', 'AIzaSyBnsrix8nUhZgQOtTgE_NZQoKn42vxBwCk'),
        'service'     => 'gcm'
    ]

];