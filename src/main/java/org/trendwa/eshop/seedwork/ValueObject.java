package org.trendwa.eshop.seedwork;

import java.util.Collection;
import java.util.Objects;

/**
 * Abstract base class for value objects.
 * Provides methods for equality and hash code generation based on the components of the value object.
 */
public abstract class ValueObject {

    /**
     * Checks if two value objects are equal using the equality operator.
     *
     * @param left  the first value object
     * @param right the second value object
     * @return true if both value objects are equal, false otherwise
     */
    protected static boolean equalOperator(ValueObject left, ValueObject right) {
        if (left == null ^ right == null) {
            return false;
        }
        return left == null || left.equals(right);
    }

    /**
     * Checks if two value objects are not equal using the inequality operator.
     *
     * @param left  the first value object
     * @param right the second value object
     * @return true if both value objects are not equal, false otherwise
     */
    protected static boolean notEqualOperator(ValueObject left, ValueObject right) {
        return !equalOperator(left, right);
    }

    /**
     * Returns the components that are used to determine equality.
     *
     * @return a collection of objects representing the components of the value object
     */
    protected abstract Collection<Object> getEqualityComponents();

    /**
     * Checks if this value object is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ValueObject other = (ValueObject) obj;

        return getEqualityComponents().equals(other.getEqualityComponents());
    }

    /**
     * Returns the hash code for this value object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return getEqualityComponents()
                .stream()
                .map(Objects::hashCode)
                .reduce(0, (x, y) -> x ^ y);
    }

    /**
     * Creates and returns a copy of this value object.
     *
     * @return a copy of this value object
     * @throws RuntimeException if the cloning is not supported
     */
    public ValueObject getCopy() {
        try {
            return (ValueObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
}