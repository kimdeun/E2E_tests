package jsonObjects.workOrder.createWorkOrder;

public class Source {
    private EntityType entityType;
    private EntityClass entityClass;

    public Source(EntityType entityType, EntityClass entityClass) {
        this.entityType = entityType;
        this.entityClass = entityClass;
    }

    public Source() {
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public EntityClass getEntityClass() {
        return entityClass;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public void setEntityClass(EntityClass entityClass) {
        this.entityClass = entityClass;
    }
}
