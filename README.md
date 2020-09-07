Arlimy 
=============

## 1. 프로그램 소개 및 개발 배경
현재 우리나라 훈련소는 크게 2가지의 인터넷 편지 제도를 채택하고 있다. 발신자가 원하는 만큼 편지를 보낼 수 있는 무제한 편지제도, 하루에 한 명만 군인에게 편지를 보낼 수 있는 1일 1편지 제도이다. 우리는 이 2가지 제도의 특성에 맞게 각각 다른 서비스를 제공하는 프로그램인 알리미(Arlimy)를 개발하기로 했다.
<br/>
무제한 부대의 군인들에게는 외부 소식을 인터넷 편지로 매일 전달해주는 서비스를 제공한다. 어차피 편지의 개수에는 제한이 없는 부대이므로, 이 프로그램을 쓰는 군인이라면 소식 전달 기능은 선택하지 않아도 처음 로그인 하는 순간 자동으로 제공해 주기로 했다. 뉴스 기사나 대나무숲 내용을 편지로 보내주는 이 기능은 ‘자동편지 기능’이다. 반면에 1일1편지 부대의 경우 편지 발송인을 위한 예약제 시스템 기능이 제공된다. 그리고 편지가 1통도 오지 않은 날에 한해서 외부 소식을 1통 받아볼 수 있는 자동 편지 기능도 추가적으로 제공된다. 
<br/>
군인들은 입대 전 이 프로그램을 통하여 자신이 받아보고 싶은 소식의 카테고리를 고르거나, 예약제 시스템을 적용할지 말지 등의 여부를 선택한다. 군인이 선택한 결과에 따라 일반인들이 편지를 보내게 된다.
<br/>
세상 소식을 알 수 없는 군인들에게 조금이나마 즐거움을 주기 위하여 소식 전달 서비스를 개발하게 되었고 1일 1편지 부대의 군인을 지인으로 둔 사람들끼리 하루에 한 번뿐인 기회를 사수하기 위해 눈치싸움 하는 것을 막기 위해 예약제 시스템을 개발하게 되었다.
<br/>
## 2. 개발 시스템 개요 
프로그램 기능의 구조도는 다음과 같다.
<img width="400" src="https://user-images.githubusercontent.com/37864097/92404948-8c8df980-f16f-11ea-922d-42192118687a.png">
<img width="400" src="https://user-images.githubusercontent.com/37864097/92404952-8e57bd00-f16f-11ea-8337-6f5274796cc3.png">


<br/>
## 3. 클래스 구조도   
<img width="500" src="https://user-images.githubusercontent.com/37864097/92403671-e2ad6d80-f16c-11ea-9405-33cf7be53103.png">
- Soldier 클래스: 각 군인의 정보를 저장하는 클래스
- ServerMain 클래스 : DB에 저장된 군인의 정보를 불러와 지정된 시간에 한꺼번에 메일을 전송하는 클래스
- MyTest 클래스 : 네이버 랭킹뉴스에서 카테고리별로 1위기사만 가져오는 클래스
- Bamboo 클래스 : 대나무숲 페이지에 접속하여 제보를 text로 가져오는 클래스
- Scheduler 클래스 : 매일 밤 11시 58분에 편지를 일괄적으로 보내기 위해 타이머를 설정하는 클래스 
- SendMail 클래스 : 실제 육군훈련소의 개인 인증을 대체하기 위해 네이버 메일을 전송하여 확인하기 위한 클래스
- UserGUI 클래스 : 인터페이스를 실행하는 클래스
