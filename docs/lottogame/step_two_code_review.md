# 1차 피드백 후 수정 사항

## InputView
- [x] : InputView 에 간단한 mapping 작업에 대한 책임 추가

## Error  

- [x] : 에러 처리 리팩토링

## Domain
- [x] : DefaultManualLottieGeneratorTest.kt 테스트명 네이밍 수정  
- [x] : 스스로 기준을 정해서 랜덤 테스트 해보기!
- [x] : LottoCount 에러 메세지 상수화하기
- [x] : LottoCount 팩토리 메서드 함수 nullable 하게 수정
- [x] : LottoResult sealed class 적용 ( manual lotto 생성할 때 사용)  
- [x] : Count value class 추가

---
# 2차 피드백 후 수정 사항

## Domain
- [x] : LottoGameResult 매직 넘버 상수화
- [x] : BonusNumber, Count 에 nullable 하지 않은 팩토리 메서드 함수 추가
- [x] : BonusNumber, Count createOrNull() 메서드 네이밍 -> ofNullable() 로 변경
- [x] : Count 삭제, Count 책임 LottoCount 로 이동
## Test
- [ ] : Test Fixture 적용하기

# 코멘트 내용

## BonusNumber, Count 에 nullable 하지 않은 팩토리 메서드 함수 추가
리팩토링 이유) 클라이언트가 매번 null 처리를 하는 것이 큰 리소스 낭비가 될 것 같다는 생각이 들었습니다.   
이에, 클라이언트가 절대 error 가 발생하지 않는다는 확신이 있을 때는, of() 를 사용하도록 리팩토링을 하였습니다!  
