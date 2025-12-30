01.Please connect your app firebase then json fill the app => jsonfile (folder creader) =>  json!! 

04.this json recevied from the firebase => Project settings=> google-service.json file recevied

2. hit on the post men---

https://fcm.googleapis.com/v1/projects/myapplication-bbee5/messages:send

#.set the Authrization => bearer token set the :: from the logcat on the *toke* or *barer token* and hit the post ment request !

3.myapplication-bbee5 => this the my projectId:

{
    "message": {
        "token": "emketHVIQyyzFNRfAYIPvp:APA91bGJ_Digc9r7hqf18OQtsnv4soPnSWBWPCI1KpFPWXKE3MKgyzgUJfqdW49LXolBVZxb96eZt3XPzw8XLPc-zlMoNw8dW-_I91T2UjGuwgvmJUXJc-Y",
        "notification": {
            "title": "Hello!",
            "body": "This is a test by hussain 5th time testing."
        }
    }
}
#_______________set the json file on the project on the app=>res=>raw (folder)

{
  "type": "service_account",
  "project_id": "myapplication-bbee5",
  "private_key_id": "a7c77ee9ebd6d9968f154e69f2c8f8969755d3e4",
  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9YXHCQVUw9O8c\nG/eFW0fnQxa5c4K6yfrFAacqIKNEIIWtZtfO6S/tAMRTx0RxAIgA3OCHB1917I+t\nPprcUMw4LvIrudUG+DQ1kYBxIgAcGkIJcm0xSJax7Zk1jyee+NTuKBE72sYaC/MI\nne8RaU2NXvmzMEp7qAQ19zvljaZCDc2JxDZiLIz1gfxqy5BKTSTwoFXvgd/p+HZR\nzHEfPRYFTmqAQkHr0pD/uL3MnpNbIRi1qPvCQzCehlkC9SHXCvgnmMSyFqMFN05x\nRnCEN5YHSn8pHuSmom3m9j+ykRYCLyZr49IKHDuCTWZpqEoo5jVl4zSAvNmLA6aV\nAPUV5L3tAgMBAAECggEABT8HTznPGdmXLCCsNTIHpmTp4r5L70+8FiwLDPQ1gaQi\n/+A69zznennMdoOtJjI9EkUMJUFziAlDG6oB0TudxKqsQ8DUoWpLy/YZaEjoVt71\nWFymoLhhkFFELsMl9lXvVB+hBpbzaPJOy7zQTBuuLc3lPe0HCzDm2PXd32iSo/7A\nmI2dwlbgFMBMlHN9IYBc8uPMTECezvd+SzfMm0dyLjQG9H9pcWz+ztj26UWf4ksb\nJrxtA2MEDcha9rgdsmNK+ew5ep/jtvtc0o1HxFmfT2taY6AVOiazen6hIuzsO8KQ\nbtbkqEBOPp/OwQrGmv8QvrD3wOhviTrtf8KQ29JkAwKBgQD5vw0JIbUATUms7Epo\nz4QcJ2p5e0SjmJc2LpwizSrpVINqW0qNdii1jlALIIxeoPd+k9gqkddirqyAlxaH\nV/aZ57DFPPr6vEd6ln9Ujrwq4+tTZxUhSObzI9wc+7efd9BDggMqTk2mHxczYpDs\ntQJcxJIKe6PVBaWI1oq6PTy6GwKBgQDCH25xb3lkzfTcral4j4OqWHCQg57gBBCg\nsrjBg1iLEwvXh9U8oC+kDIzCboQeUYk2mkvPvCfq3zmOGYJ3dJNvfiIJWWsjVR+E\nDl9ISZvl80PXplWBV2Woa82Mvpi3zSqdnyr3hhaRlxhkrwcOzWfvyi3rgBm7CEx1\nNoVfGeholwKBgCK/H/MrS2F1X1C9t0SJypmYKkooik4whqk1Juf4n4XDCl2bwe2Y\niOVpnV1O8/iecJCdxS93Bv5lyv5BBxNhe5/CCmgGqeyEkXVOxvxet3NILo92AgyV\ndfxR+ufVZaC8LK+XwRT7u/Xm0XWGux8Tt/ZsKt1mgk668GLvZnGu32oNAoGBAJM7\nF579d0rXUoIVTNXds3T9gtJ4oYUyUBIxuyM6JWvF1FNx+G705206/39wJtB/8tvx\nBtLV2EixaYu3GVbUmgYT2mjQ4wt3QmH8LJQ9D/rGGIx04aEp0jZ9HPdEj7vH5iyK\nTiJSFsfxm1Sx016Mt+oMo+tbyygp0SONaHaKxdSdAoGBANou5fflk1NUp0kdjDMp\nJvithXIlThfvjUkJq2EFAmXFrlMZMR7iTP3tON4ZjwvM89AfHnMlegxiWf/LWacW\n9DaXyfvPfZYsFnvfTw6HLsUp9uSNQjv2VlSSueGai2jB4kjYwDtdslco6Og7JaXy\nYFGdCxqzoz6soJ4Nf8Q7ow+w\n-----END PRIVATE KEY-----\n",
  "client_email": "firebase-adminsdk-b30n5@myapplication-bbee5.iam.gserviceaccount.com",
  "client_id": "1080858293564092092**",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.google![notification](https://github.com/user-attachments/assets/2007d952-5c85-453d-a30d-890fed731239)
apis.com/robot/v1/metadata/x509/firebase-adminsdk-b30n5%40myapplication-bbee5.iam.gserviceaccount.com",
  "universe_domain": "googleapis.com"
}

_________________________________________________________________________________________________________________________________________________
#.    see the image of the Notification on andorid phone
   

<img width="900" height="634" alt="Screenshot 2025-08-27 at 10 43 18 AM" src="https://github.com/user-attachments/assets/e83b09da-2aee-4e83-932d-a07aa8a71b7b" />
assets/fb3ebd43-fcbd-4b94-ab79-fb6fac8d55a4" />


<img width="901" height="609" alt="Screenshot 2025-08-27 at 10 43 36 AM" src="https://github.com/user-attachments/assets/23e365d5-e3fc-4c8c-a31a-371b89a7dd86" />

<img width="329" height="733" alt="Screenshot 2025-08-27 at 10 46 32 AM" src="https://github.com/user-attachments/assets/17d7d90d-f899-4972-b242-40269f3e9389" />

<img width="336" height="727" alt="Screenshot 2025-08-27 at 10 47 28 AM" src="https://github.com/user-attachments/assets/99c0f404-ba19-43a6-8c6c-54576e0faffe" />

![WhatsApp Image 2025-10-14 at 17 33 09](https://github.com/user-attachments/assets/12cbeffe-5928-4004-a74c-d1ea55fc59b6)


<img width="1155" height="577" alt="Screenshot 2025-10-15 at 1 28 14 PM" src="https://github.com/user-attachments/assets/72be66f3-7375-4a8c-94f4-2e13af66b2a9" />
