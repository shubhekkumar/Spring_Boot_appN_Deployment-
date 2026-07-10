resource "aws_security_group" "ecs" {

  name = "${var.project_name}-${var.environment}-sg"

  description = "Security Group for TaskMaster ECS"

  vpc_id = var.vpc_id

  ingress {

    description = "HTTP"

    from_port = 80

    to_port = 80

    protocol = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }

  ingress {

    description = "HTTPS"

    from_port = 443

    to_port = 443

    protocol = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }

  ingress {

    description = "Spring Boot"

    from_port = 8080

    to_port = 8080

    protocol = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }

  egress {

    from_port = 0

    to_port = 0

    protocol = "-1"

    cidr_blocks = ["0.0.0.0/0"]

  }

  tags = {

    Name = "${var.project_name}-${var.environment}-sg"

  }

}
