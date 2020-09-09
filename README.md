Arlimy 
=============

## 1. 프로그램 소개 및 개발 배경
현재 우리나라 훈련소는 크게 2가지의 인터넷 편지 제도를 채택하고 있다. 발신자가 원하는 만큼 편지를 보낼 수 있는 무제한 편지제도, 하루에 한 명만 군인에게 편지를 보낼 수 있는 1일 1편지 제도이다. 우리는 이 2가지 제도의 특성에 맞게 각각 다른 서비스를 제공하는 프로그램인 알리미(Arlimy)를 개발하기로 했다.
<br/><br/>
무제한 부대의 군인들에게는 외부 소식을 인터넷 편지로 매일 전달해주는 서비스를 제공한다. 어차피 편지의 개수에는 제한이 없는 부대이므로, 이 프로그램을 쓰는 군인이라면 소식 전달 기능은 선택하지 않아도 처음 로그인 하는 순간 자동으로 제공해 주기로 했다. 뉴스 기사나 대나무숲 내용을 편지로 보내주는 이 기능은 ‘자동편지 기능’이다. 반면에 1일1편지 부대의 경우 편지 발송인을 위한 예약제 시스템 기능이 제공된다. 그리고 편지가 1통도 오지 않은 날에 한해서 외부 소식을 1통 받아볼 수 있는 자동 편지 기능도 추가적으로 제공된다. 
<br/><br/>
군인들은 입대 전 이 프로그램을 통하여 자신이 받아보고 싶은 소식의 카테고리를 고르거나, 예약제 시스템을 적용할지 말지 등의 여부를 선택한다. 군인이 선택한 결과에 따라 일반인들이 편지를 보내게 된다.
<br/>
세상 소식을 알 수 없는 군인들에게 조금이나마 즐거움을 주기 위하여 소식 전달 서비스를 개발하게 되었고 1일 1편지 부대의 군인을 지인으로 둔 사람들끼리 하루에 한 번뿐인 기회를 사수하기 위해 눈치싸움 하는 것을 막기 위해 예약제 시스템을 개발하게 되었다.
<br/><br/>
## 2. 개발 시스템 개요 
프로그램 기능의 구조도는 다음과 같다.<br/>
<img width="400" src="https://user-images.githubusercontent.com/37864097/92405294-3b323a00-f170-11ea-97fc-440e74829612.png"> <br/>
<img width="400" src="https://user-images.githubusercontent.com/37864097/92404952-8e57bd00-f16f-11ea-8337-6f5274796cc3.png"><br/>
부대의 특성별로 나누어 기능을 구현했다. 무제한 편지 또는 1일 1편지 라는 큰 틀은 군 부대 자체적으로 결정한 사항이기 때문에 우리 자체적으로 수정할 수 없는 것이라 두가지 틀은 그대로 놔둔 채로 추가 기능을 제공한다.<br/><br/>
우선 무제한 부대이다. 무제한 부대에는 자동으로 뉴스기사나 대나무숲 글을 보내주는 자동 편지 발송 기능이 추가된다.<br/><br/>
그래서 무제한 부대의 군인의 경우 자동으로 자동 편지 기능이 제공되므로, 자신이 받아보고 싶은 소식의 카테고리를 고르면 된다.
무제한 부대의 군인에게 편지를 쓰고 싶은 ‘일반인’의 경우 자동 편지 기능을 직접 사용해 볼 수도 있다. 즉, 뉴스나 대나무숲을 알리미 프로그램이 아닌 발신자가 직접 보내는 것이다. <br/><br/>
그리고 1일 1편지 부대이다. 이 부대에는 편지 발송자가 날짜를 예약하는 예약제 시스템이 추가되고, 도착한 편지가 없는 날에 한해 자동편지 기능도 추가된다. <br/><br/>
1일1편지 부대 군인의 경우 자신이 예약제 시스템을 신청할지 말지 선택한다. 예약제를 선택한 군인의 경우 그 군인에게 편지를 쓰려는 일반인은 편지 보내고 싶은 날을 미리 예약을 해야만 편지를 보낼 수 있다. 매주 주말 이틀 동안에만 그 다음주 1주일간의 예약이 열린다. 한 사람당 일주일에 이틀만 예약 가능하다. 즉 군인에게 편지를 보낼 수 있는 사람은 하루에 그 날 예약한 한 명 뿐이다. 예약은 일주일 중에 최대 2일만 가능하다. <br/><br/>
그리고 1일1편지 부대 군인이 자동편지 발송을 신청하고 입대하면, 편지가 없는 날에만 자동적으로 뉴스 또는 대숲 글을 보내준다. 
현재 실제 훈련소 인터넷 편지는 일반 편지쓰기 기능만을 제공하고 있는데, 알리미에서는 두 부대 모두 일반 편지 작성 기능은 기본적으로 포함하고 있다. 


<br/><br/>
## 3. 클래스 구조도   
<img width="500" src="https://user-images.githubusercontent.com/37864097/92403671-e2ad6d80-f16c-11ea-9405-33cf7be53103.png">
- Soldier 클래스: 각 군인의 정보를 저장하는 클래스<br/>
- ServerMain 클래스 : DB에 저장된 군인의 정보를 불러와 지정된 시간에 한꺼번에 메일을 전송하는 클래스<br/>
- MyTest 클래스 : 네이버 랭킹뉴스에서 카테고리별로 1위기사만 가져오는 클래스<br/>
- Bamboo 클래스 : 대나무숲 페이지에 접속하여 제보를 text로 가져오는 클래스<br/>
- Scheduler 클래스 : 매일 밤 11시 58분에 편지를 일괄적으로 보내기 위해 타이머를 설정하는 클래스 <br/>
- SendMail 클래스 : 실제 육군훈련소의 개인 인증을 대체하기 위해 네이버 메일을 전송하여 확인하기 위한 클래스<br/>
- UserGUI 클래스 : 인터페이스를 실행하는 클래스<br/>

<br/><br/>

## 4. 실행화면  
<div class="parent" style="width: 500px; height: 250px;">
    <div class="child"img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612262-ac9af580-f2f4-11ea-8e46-9cf094215a5a.png">

    </div>
    <div class="child" img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612267-adcc2280-f2f4-11ea-9890-f45fe538028f.png">

    </div>
</div>
<img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612262-ac9af580-f2f4-11ea-8e46-9cf094215a5a.png">
<img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612267-adcc2280-f2f4-11ea-9890-f45fe538028f.png">
<br/>
무제한 편지 부대 군인의 경우<br/>
<img width="250" height="250"src="https://user-images.githubusercontent.com/37864097/92612269-adcc2280-f2f4-11ea-8647-e572861bbd1a.png"><img width="300" height="300"src="https://user-images.githubusercontent.com/37864097/92612272-ae64b900-f2f4-11ea-94a0-630267084bad.png">

<br/>
무제한 편지 부대 일반인의 경우<br/>
<img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612273-ae64b900-f2f4-11ea-831c-9fec6912b4e8.png">
<img width="250" height="250"src="https://user-images.githubusercontent.com/37864097/92612274-aefd4f80-f2f4-11ea-9a66-e6d9385389ac.png">
<img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612276-aefd4f80-f2f4-11ea-8524-a523b9b22bd9.png">

<br/>
1일 1편지 군인의 경우 <br/>
<img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612867-42cf1b80-f2f5-11ea-903c-62e97ac81ac7.png">
<br/>
1일 1편지 일반인의 경우 <br/>
<img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612869-4367b200-f2f5-11ea-92a5-76ab688dc799.png">
<img width="250" height="250" src="https://user-images.githubusercontent.com/37864097/92612872-44004880-f2f5-11ea-8fa3-708b58419ece.png">


<br/><br/>
## 5. 포함해야할 라이브러리  
- Activation.jar<br/>
- Javax.mail.jar<br/>
- Jsoup-1.11.2.jar<br/>
- Jsoup-1.11.2-javadoc.jar<br/>
- Mail.jar<br/>
- Mariadb-java-client-2.2.0.jar<br/>
