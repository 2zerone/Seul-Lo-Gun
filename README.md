# Seul-Lo-Gun
Final project for Computer engineering

e-mail : dudgks3316@naver.com

## Introduction
> 학교를 다니며 적지않은 4년이라는 시간동안 좋은 추억들이 많이 있습니다.
>
> 이러한 것들은 경험을 통해 쌓여진 것들이고, 좀 더 일찍 알면 좋겠다 싶었던 부분들 중 한가지가 음식점들에 관한 정보였습니다.
> 학교특성상 주변의 음식점들에대한 정보가 많지않고 정보가 없는 신입생, 편입생분들은 값싸고 맛있는 음식점을 찾기 어렵기에 
> 음식점에대한 간단한 정보와 주문, 리뷰를 쓸 수 있는 어플을 개발하게 되었습니다. 
>
> 또한, 학우들간의 원할한 의사소통과 정보교환을 위해 게시판형식의 정보를 제공하여 보다 편리한 학교생활을 추구하고자 계획하게 되었습니다.

> I have a lot of good  memories during the four years of school.
>
> These were accumulated through experience, and one of the things I wanted to know earlier was about restaurants.
> Due to the characteristics of the school, there is not much information about the restaurants around, and it is difficult for  
> freshmen and transfer students who do not have information to find cheap and delicious restaurants.
> I developed an application that allows me to write simple information, order, and review about restaurants.
>
> In addition, we plan to pursue a more convenient school life by providing information in the form of bulletin boards for smooth 
> communication and information exchange among students.

## 기능설명 
[개인정보 기능] 
1. Sign up , Sign in
2. Reset Password 
    - 회원가입시 작성한 이메일 주소로 비밀번호 재설정가능
  
[음식점기능]
1. List 
    - 음식점들의 상호명, 간단한 정보를 포함한 정보확인이 가능한 기능
2. Call 
    - 주문하기 아이콘을 통한 가게로의 전화 연결 기능
3. Review 
    - 사용후 별점과 후기를 작성할 수 있는 기능

[게시판 기능]
1. List(all)
    - 게시글의 제목, 내용, 사진, 동영상을 볼 수 있는 기능
2. add
    - 게시물을 작성할 수 있는 기능 (제목,내용 ,사진 및 동영상 첨부 가능)
3. Del
    - 본인이 작성한 게시글을 삭제할 수 있는 기능
4. List(Review)
    - 게시글의 댓글정보를 볼 수 있는 기능( 댓글 작성자의 이미지 및 닉네임포함) 
5. Review 
    - 다른 게시글에 댓글을 달 수 있는 기능
6. Review del 
    - 자신이 쓴 댓글을 지울 수 있는 기능

[내 정보기능]
1. Profile
    - 나의 이름, 전화번호, 주소, 프로필 사진을 확인할 수 있는 기능
2. Logout
    - 로그아웃 

## Development Environment
- Andrioid Studio @4.0

## Application Version
- minSdkVersion : 21
- targetSdkVersion : 30

## code Example
     BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
                        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
                                @Override
                                public boolean onNavigationItemSelected(@NonNull MenuItem item){
                                        switch(item.getItemId()){
                                                case R.id.home:
                                                        Log.e("홈","홈");
                                                        HomeFragment homeFragment = new HomeFragment();
                                                        getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.container, homeFragment)
                                                                .commit();
                                                        setToolbarTitle(getResources().getString(R.string.home));
                                                        return true;
                                                case R.id.food:
                                                        Log.e("음식점","음식점");
                                                        FoodStoreFragment foodStoreFragment = new FoodStoreFragment();
                                                        getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.container, foodStoreFragment)
                                                                .commit();
                                                        setToolbarTitle(getResources().getString(R.string.food));
                                                        return true;

                                                case R.id.communication:
                                                        Log.e("자유게시판","자유게시판");
                                                        CommunicationFragment communicationFragment = new CommunicationFragment();
                                                        getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.container, communicationFragment)
                                                                .commit();
                                                        setToolbarTitle(getResources().getString(R.string.com));
                                                        return true;

                                                case R.id.Trade:
                                                        Log.e("중고거래","중고거래");
                                                        TradeFragment tradeFragment = new TradeFragment();
                                                        getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.container, tradeFragment)
                                                                .commit();
                                                        setToolbarTitle(getResources().getString(R.string.trade));
                                                        return true;

                                                case R.id.userinfo:
                                                        Log.e("내정보","내정보");
                                                        UesrinfoFragment uesrinfoFragment = new UesrinfoFragment();
                                                        getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.container, uesrinfoFragment)
                                                                .commit();
                                                        setToolbarTitle(getResources().getString(R.string.uesrinfo));
                                                        return true;

## scrennshot
<div>
<img src="https://user-images.githubusercontent.com/58229545/103470863-7dd0ef80-4dbb-11eb-8e44-dde849bbcd7f.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470875-e4eea400-4dbb-11eb-98e7-641bad0a1718.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470876-e61fd100-4dbb-11eb-9809-14ed23660f4d.png" width="200">    
<img src="https://user-images.githubusercontent.com/58229545/103470878-e8822b00-4dbb-11eb-8785-bbb4f1fa7ea4.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470879-ec15b200-4dbb-11eb-8ce1-71b62b657ebe.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470891-0fd8f800-4dbc-11eb-99e3-88c0845e281f.jpg" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470899-20896e00-4dbc-11eb-8532-272f2fc128ea.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470904-36972e80-4dbc-11eb-973f-de0fb1961add.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470906-44e54a80-4dbc-11eb-9102-b7a6880d2a8e.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470907-46167780-4dbc-11eb-97af-428e454f6faf.png" width="200">
<img src="https://user-images.githubusercontent.com/58229545/103470909-49116800-4dbc-11eb-8ca1-5ae113675a2e.png" width="200">
    
<div>
