resource "aws_ecs_cluster" "main" {

  name = "${var.project_name}-${var.environment}-cluster"

  setting {

    name = "containerInsights"

    value = "disabled"

  }

  tags = {

    Name = "${var.project_name}-${var.environment}-cluster"

  }

}


resource "aws_ecs_task_definition" "taskmaster" {

  family = "${var.project_name}-${var.environment}"

  requires_compatibilities = ["FARGATE"]

  network_mode = "awsvpc"

  cpu = "256"

  memory = "512"

  execution_role_arn = var.execution_role_arn

  task_role_arn = var.task_role_arn

  container_definitions = jsonencode([
    {
      name = "${var.project_name}-container"

      image = "${var.ecr_repository_url}:latest"

      essential = true

      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
          protocol      = "tcp"
        }
      ]

      logConfiguration = {

        logDriver = "awslogs"

        options = {

          awslogs-group         = var.log_group_name
          awslogs-region        = var.aws_region
          awslogs-stream-prefix = "ecs"

        }

      }

    }
  ])
}
