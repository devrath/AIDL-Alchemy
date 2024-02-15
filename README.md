<h1 align="center">𝙰𝙸𝙳𝙻-𝙰𝚕𝚌𝚑𝚎𝚖𝚢</h1>

![Untitled Diagram drawio](https://github.com/devrath/AIDL-Alchemy/assets/1456191/5a4b7484-cbdc-47ef-9c4d-158b2fc05b3a)

🗄️ 𝚃𝚑𝚒𝚜 𝚛𝚎𝚙𝚘𝚜𝚒𝚝𝚘𝚛𝚢 𝚐𝚞𝚒𝚍𝚎𝚜 𝚞𝚝𝚒𝚕𝚒𝚣𝚒𝚗𝚐 𝙰𝚗𝚍𝚛𝚘𝚒𝚍 𝙸𝚗𝚝𝚎𝚛𝚏𝚊𝚌𝚎 𝙳𝚎𝚏𝚒𝚗𝚒𝚝𝚒𝚘𝚗 𝙻𝚊𝚗𝚐𝚞𝚊𝚐𝚎 (𝙰𝙸𝙳𝙻) 𝚊𝚗𝚍 𝚒𝚗𝚌𝚕𝚞𝚍𝚎𝚜 𝚌𝚘𝚖𝚙𝚛𝚎𝚑𝚎𝚗𝚜𝚒𝚟𝚎 𝚌𝚘𝚗𝚝𝚎𝚗𝚝 𝚌𝚘𝚟𝚎𝚛𝚒𝚗𝚐 𝚌𝚘𝚗𝚌𝚎𝚙𝚝𝚜 𝚊𝚗𝚍 𝚝𝚘𝚙𝚒𝚌𝚜 𝚛𝚎𝚕𝚊𝚝𝚎𝚍 𝚝𝚘 𝙰𝙸𝙳𝙻 𝚒𝚗 𝙰𝚗𝚍𝚛𝚘𝚒𝚍 𝚍𝚎𝚟𝚎𝚕𝚘𝚙𝚖𝚎𝚗𝚝.

<div align="center">
  
| **`𝙲𝚘𝚗𝚝𝚎𝚗𝚝𝚜`** |
| ---------- |
| [`Demo`](https://github.com/devrath/AIDL-Alchemy/blob/main/README.md#output) |
| [`About AIDL`](https://github.com/devrath/AIDL-Alchemy/blob/main/README.md#about-aidl) |
| [`How communication occurs in the sample`](https://github.com/devrath/AIDL-Alchemy/blob/main/README.md#how-communication-occurs-in-the-sample) |
| [`How to run the sample application`](https://github.com/devrath/AIDL-Alchemy/blob/main/README.md#how-to-run-sample-application-) |

</div>

## `Output`
<div align="center">
  
https://github.com/devrath/AIDL-Alchemy/assets/1456191/7577637c-0782-4b2e-bf55-4ee2a3530e2c

</div>



## `About AIDL`
* `AIDL` is called `Android Interface Definition Language`.
* It acts as an interface between the server and the client.
* Both server and client agree to communicate using this interface.

#### `Why AIDL is necessary`
* In Android normally processes do not communicate with each other.
* If we want the processes to communicate with each other, We need to convert the data to primitives that the operating system can understand and then communicate, It is complex --> The AIDL makes this process simple to achieve.

#### `When to use AIDL`
* Any method defined in your service to be accessed simultaneously by more than one applications.
* If you want to share the data from your application so that other applications access it.

#### `The data types supported by AIDL`
* All the primitive types like `int`, `char`, `boolean`, `long`, and so on...
* `String`
* `CharSequence`
* `List<Supported data type>` 
* `Map<Supported data type>`
* `Generic maps`, such as `Map<String,Integer>` are not supported.

## `How communication occurs in the sample`
**`1`** _**Server application**_
* In the server application, We shall create an `aidl` file interface with methods exposed where on building the interface the code is generated behind the scenes by Android that takes care of marshaling the data that is used for communication.
* Server defines a service class that extends an android `service` class and we `Override` the `onBind` method.
* In the usual case of normally running the background service, We would return the `IBinder` as `null` but in our case here we will write an `IBinder` implementation.
* The `IBinder` implementation will have all the abstract methods overridden and defined where we define the server implementations.
  
**`2`** _**Client application**_
* In the client also define the same `aidl` file as a server(You can even copy-paste it).
* Now important to note is the package hierarchy must be the same here as the server else it won't work.
* Build the project so that the Android generates code behind the scenes.
* Define a service connection implementation
  * Here there are two methods `onServiceConnected` and `onServiceDisconnected` methods overridden.
  * At `onServiceConnected` method we get the reference to `interface reference` which requires `IBinder` as input to it and the reference is kept globally.
  * Using this reference we can access and communicate with the server.

**`3`** _**Where we define the aidl files**_

| `Server` | `Client` |
| -------- | -------- |
| <img width="354" alt="Screenshot 2024-02-15 at 11 32 52 PM" src="https://github.com/devrath/AIDL-Alchemy/assets/1456191/3b46ea78-3637-454e-9199-64c11819e0a0"> | <img width="354" alt="Screenshot 2024-02-15 at 11 33 35 PM" src="https://github.com/devrath/AIDL-Alchemy/assets/1456191/80cbc402-be3f-4407-812c-4e674108f9a9"> |

   
**`4`** _**How client initiates communication with server**_
* We prepare an intent with inputs
  * It has an `Intent filter name`
  * The `package name` of the server
  * The `component name` that contains the service name in the server.
* We call the `bindService` that has `intent`,`service connection`, and a `flag` to initiate the connection.
* The earlier kept global reference is used to call the interface methods which in turn communicates with the server and gets the result from the server.
  

## `How to run sample application` 👣
* There are two applications in the repository. One is for `server` and another for `client`
* Server consists of a `service` and does not contain any activity, client contains an `activity` that contains a button to initiate data retrieval from the server application.
* Install the server App and install the client app also.
* Now click the specific buttons on the client to retrieve specific data from the server. 

## **`𝚂𝚞𝚙𝚙𝚘𝚛𝚝`** ☕
𝙸𝚏 𝚢𝚘𝚞 𝚏𝚎𝚎𝚕 𝚕𝚒𝚔𝚎 𝚜𝚞𝚙𝚙𝚘𝚛𝚝 𝚖𝚎 𝚊 𝚌𝚘𝚏𝚏𝚎𝚎 𝚏𝚘𝚛 𝚖𝚢 𝚎𝚏𝚏𝚘𝚛𝚝𝚜, 𝙸 𝚠𝚘𝚞𝚕𝚍 𝚐𝚛𝚎𝚊𝚝𝚕𝚢 𝚊𝚙𝚙𝚛𝚎𝚌𝚒𝚊𝚝𝚎 𝚒𝚝.</br>
<a href="https://www.buymeacoffee.com/devrath" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/yellow_img.png" alt="𝙱𝚞𝚢 𝙼𝚎 𝙰 𝙲𝚘𝚏𝚏𝚎𝚎" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>

## **`𝙲𝚘𝚗𝚝𝚛𝚒𝚋𝚞𝚝𝚎`** 🙋‍♂️
𝚁𝚎𝚊𝚍 [𝚌𝚘𝚗𝚝𝚛𝚒𝚋𝚞𝚝𝚒𝚘𝚗 𝚐𝚞𝚒𝚍𝚎𝚕𝚒𝚗𝚎𝚜](CONTRIBUTING.md) 𝚏𝚘𝚛 𝚖𝚘𝚛𝚎 𝚒𝚗𝚏𝚘𝚛𝚖𝚊𝚝𝚒𝚘𝚗 𝚛𝚎𝚐𝚊𝚛𝚍𝚒𝚗𝚐 𝚌𝚘𝚗𝚝𝚛𝚒𝚋𝚞𝚝𝚒𝚘𝚗.

## **`𝙵𝚎𝚎𝚍𝚋𝚊𝚌𝚔`** ✍️
𝙵𝚎𝚊𝚝𝚞𝚛𝚎 𝚛𝚎𝚚𝚞𝚎𝚜𝚝𝚜 𝚊𝚛𝚎 𝚊𝚕𝚠𝚊𝚢𝚜 𝚠𝚎𝚕𝚌𝚘𝚖𝚎, [𝙵𝚒𝚕𝚎 𝚊𝚗 𝚒𝚜𝚜𝚞𝚎 𝚑𝚎𝚛𝚎](https://github.com/devrath/AIDL-Alchemy/issues/new).

## **`𝙵𝚒𝚗𝚍 𝚝𝚑𝚒𝚜 𝚙𝚛𝚘𝚓𝚎𝚌𝚝 𝚞𝚜𝚎𝚏𝚞𝚕`** ? ❤️
𝚂𝚞𝚙𝚙𝚘𝚛𝚝 𝚒𝚝 𝚋𝚢 𝚌𝚕𝚒𝚌𝚔𝚒𝚗𝚐 𝚝𝚑𝚎 ⭐ 𝚋𝚞𝚝𝚝𝚘𝚗 𝚘𝚗 𝚝𝚑𝚎 𝚞𝚙𝚙𝚎𝚛 𝚛𝚒𝚐𝚑𝚝 𝚘𝚏 𝚝𝚑𝚒𝚜 𝚙𝚊𝚐𝚎. ✌️

## **`𝙻𝚒𝚌𝚎𝚗𝚜𝚎`** ![Licence](https://img.shields.io/github/license/google/docsy) :credit_card:
𝚃𝚑𝚒𝚜 𝚙𝚛𝚘𝚓𝚎𝚌𝚝 𝚒𝚜 𝚕𝚒𝚌𝚎𝚗𝚜𝚎𝚍 𝚞𝚗𝚍𝚎𝚛 𝚝𝚑𝚎 𝙰𝚙𝚊𝚌𝚑𝚎 𝙻𝚒𝚌𝚎𝚗𝚜𝚎 𝟸.𝟶 - 𝚜𝚎𝚎 𝚝𝚑𝚎 [𝙻𝙸𝙲𝙴𝙽𝚂𝙴](https://github.com/devrath/AIDL-Alchemy/blob/main/LICENSE) 𝚏𝚒𝚕𝚎 𝚏𝚘𝚛 𝚍𝚎𝚝𝚊𝚒𝚕𝚜.


<p align="center">
<a><img src="https://forthebadge.com/images/badges/built-for-android.svg"></a>
</p>
