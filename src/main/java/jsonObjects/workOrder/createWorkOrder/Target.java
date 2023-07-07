package jsonObjects.workOrder.createWorkOrder;

public class Target {
    EntityType entityType;
    EntityClass entityClass;

    public Target(EntityType entityType, EntityClass entityClass) {
        this.entityType = entityType;
        this.entityClass = entityClass;
    }

    public Target() {
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public EntityClass getEntityClass() {
        return entityClass;
    }

}
