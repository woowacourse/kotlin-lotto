package lotto

import org.junit.jupiter.api.Test

class LottoTest {
    //    - 로또를 발행하는 기기
//  - [ ] 로또 구입 개수만큼 로또를 발행한다.
//    - [ ] 1에서 45까지 숫자를 한개 뽑는다.
//    - [ ] 리스트 원소가 6개가 될때까지, 중복 없이 숫자를 추가한다.

    @Test
    fun `1에서 45까지 숫자를 한개 뽑는다`() {
        val number = (1..45).random()
    }
}
