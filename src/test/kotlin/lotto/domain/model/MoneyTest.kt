package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(longs = [-1, -999])
    fun `구입 금액이 0원 미만이면 예외를 던진다`(value: Long) {
        val exception = assertThrows<IllegalArgumentException> { Money(value) }
        assertThat(exception.message).isEqualTo("0원 이상이어야 합니다.")
    }

    @Nested
    @DisplayName("입력 금액과 물건 가격으로 수량을 계산하는 calculateQuantity 메서드 테스트")
    inner class CalculateQuantityTest {

        @Test
        fun `입력 금액이 1234원, 물건 가격이 1000원일 때 수량을 1개 반환하는지 테스트`() {
            val result = Money(1234).calculateQuantity(1000)
            assertThat(result).isEqualTo(1)
        }

        @Test
        fun `입력 금액이 10000원, 물건 가격이 1000원일 때 수량을 10개 반환하는지 테스트`() {
            val result = Money(10000).calculateQuantity(1000)
            assertThat(result).isEqualTo(10)
        }
    }
}
