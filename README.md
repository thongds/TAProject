#TAProject

1. Gilde is third-party used in this project, because Glile support cancel all request when activity or fragment destroy
2. The limitations in my project :

	a. When user scroll infinitely and ten thousand user use at same time, it can be become an DDOS attacking. So we should cancel all request from invisible View Items, but Glide doesnt support this case. If you want reslove this issue, I can create own ImageLoader and use Worker and Schedule (new feature from android framework) 
