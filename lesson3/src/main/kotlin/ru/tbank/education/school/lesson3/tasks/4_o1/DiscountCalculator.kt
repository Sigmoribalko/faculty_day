// я хочу добавить еще один тип студента, которому дают скидку
// но не хочу раздувать when
// придумайте как это можно решить
//class DiscountCalculator {
//    fun calculateDiscount(studentType: String, price: Int): Int {
//        return when (studentType) {
//            "отличник" -> (price * 0.5).toInt()   // 50% скидка
//            "льготник" -> (price * 0.7).toInt()   // платит 70%
//            "обычный"  -> price                   // без скидки
//            else       -> price                   // неизвестный тип
//        }
//    }
// }

interface DiscountCalculator {
    fun calculateDiscount (price: int) : Int
}

class ExellentStudent: DiscountCalculator {
    override fun calculateDiscount (price: int): Int {
        return (price * 0.5).toInt()
    }
}

class BeneficiaryStudent: DiscountCalculator {
    override fun calculateDiscount (price: int): Int {
        return (price * 0.7).toInt()
    }
}

class CasualStudent: DiscountCalculator {
    override fun calculateDiscount (price: int): Int {
        return (price)
    }
}

class StudentWithachievments: DiscountCalculator {
    override fun calculateDiscount (price: int): Int {
        return 0
    }
}