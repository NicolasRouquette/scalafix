package scalafix.v1

import scala.meta.Tree
import scala.runtime.Statics
import scalafix.internal.util.Pretty
import scalafix.util.FieldNames

sealed abstract class STree extends Product with FieldNames {
  final override def toString: String = Pretty.pretty(this).render(80)
  final def isEmpty: Boolean = this == NoTree
  final def nonEmpty: Boolean = !isEmpty
}

case object NoTree extends STree

final class IdTree private[scalafix] (
    val symbol: Symbol
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 1
  override def productPrefix: String = "IdTree"
  override def productElement(n: Int): Any = n match {
    case 0 => symbol
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "symbol"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean = that.isInstanceOf[IdTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: IdTree =>
        this.symbol == s.symbol
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(symbol))
    Statics.finalizeHash(acc, 1)
  }
}

final class SelectTree private[scalafix] (
    val qualifier: STree,
    val id: IdTree
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 2
  override def productPrefix: String = "SelectTree"
  override def productElement(n: Int): Any = n match {
    case 0 => qualifier
    case 1 => id
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "qualifier"
    case 1 => "id"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean = that.isInstanceOf[SelectTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: SelectTree =>
        this.qualifier == s.qualifier &&
          this.id == s.id
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(qualifier))
    acc = Statics.mix(acc, Statics.anyHash(id))
    Statics.finalizeHash(acc, 2)
  }
}

final class ApplyTree private[scalafix] (
    val function: STree,
    val arguments: List[STree]
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 2
  override def productPrefix: String = "ApplyTree"
  override def productElement(n: Int): Any = n match {
    case 0 => function
    case 1 => arguments
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "function"
    case 1 => "arguments"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean = that.isInstanceOf[ApplyTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: ApplyTree =>
        this.function == s.function &&
          this.arguments == s.arguments
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(function))
    acc = Statics.mix(acc, Statics.anyHash(arguments))
    Statics.finalizeHash(acc, 2)
  }
}

final class TypeApplyTree private[scalafix] (
    val function: STree,
    val typeArguments: List[SType]
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 2
  override def productPrefix: String = "TypeApplyTree"
  override def productElement(n: Int): Any = n match {
    case 0 => function
    case 1 => typeArguments
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "function"
    case 1 => "typeArguments"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean = that.isInstanceOf[TypeApplyTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: TypeApplyTree =>
        this.function == s.function &&
          this.typeArguments == s.typeArguments
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(function))
    acc = Statics.mix(acc, Statics.anyHash(typeArguments))
    Statics.finalizeHash(acc, 2)
  }
}

final class FunctionTree private[scalafix] (
    val parameters: List[IdTree],
    val body: STree
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 2
  override def productPrefix: String = "FunctionTree"
  override def productElement(n: Int): Any = n match {
    case 0 => parameters
    case 1 => body
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "parameters"
    case 1 => "body"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean = that.isInstanceOf[FunctionTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: FunctionTree =>
        this.parameters == s.parameters &&
          this.body == s.body
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(parameters))
    acc = Statics.mix(acc, Statics.anyHash(body))
    Statics.finalizeHash(acc, 2)
  }
}

final class LiteralTree private[scalafix] (
    val constant: Constant
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 1
  override def productPrefix: String = "LiteralTree"
  override def productElement(n: Int): Any = n match {
    case 0 => constant
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "constant"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean = that.isInstanceOf[LiteralTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: LiteralTree =>
        this.constant == s.constant
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(constant))
    Statics.finalizeHash(acc, 1)
  }
}

final class MacroExpansionTree private[scalafix] (
    val beforeExpansion: STree,
    val tpe: SType
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 2
  override def productPrefix: String = "MacroExpansionTree"
  override def productElement(n: Int): Any = n match {
    case 0 => beforeExpansion
    case 1 => tpe
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "beforeExpansion"
    case 1 => "tpe"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean =
    that.isInstanceOf[MacroExpansionTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: MacroExpansionTree =>
        this.beforeExpansion == s.beforeExpansion &&
          this.tpe == s.tpe
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(beforeExpansion))
    acc = Statics.mix(acc, Statics.anyHash(tpe))
    Statics.finalizeHash(acc, 2)
  }
}

final class OriginalTree private[scalafix] (
    val matchesFullOriginalRange: Boolean,
    val tree: Tree
)(implicit doc: SemanticDocument)
    extends STree {
  override def productArity: Int = 1
  override def productPrefix: String = "OriginalTree"
  override def productElement(n: Int): Any = n match {
    case 0 => tree
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def fieldName(n: Int): String = n match {
    case 0 => "tree"
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }
  override def canEqual(that: Any): Boolean =
    that.isInstanceOf[OriginalTree]
  override def equals(obj: Any): Boolean =
    this.eq(obj.asInstanceOf[AnyRef]) || (obj match {
      case s: OriginalTree =>
        this.tree == s.tree
      case _ => false
    })
  override def hashCode(): Int = {
    var acc = -889275714
    acc = Statics.mix(acc, Statics.anyHash(tree))
    Statics.finalizeHash(acc, 1)
  }
}
