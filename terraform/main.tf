module "network" {

  source = "./modules/network"

  project_name = var.project_name
  environment  = var.environment

}

module "security" {

  source = "./modules/security"

  project_name = var.project_name

  environment = var.environment

  vpc_id = module.network.vpc_id

}

module "iam" {

  source = "./modules/iam"

  project_name = var.project_name

  environment = var.environment

}

module "ecr" {

  source = "./modules/ecr"

  project_name = var.project_name

  environment = var.environment

}

module "cloudwatch" {

  source = "./modules/cloudwatch"

  project_name = var.project_name

  environment = var.environment

}

module "ecs" {

  source = "./modules/ecs"

  project_name = var.project_name

  environment = var.environment

  aws_region = var.aws_region


  execution_role_arn = module.iam.execution_role_arn

  task_role_arn = module.iam.task_role_arn


  log_group_name = module.cloudwatch.log_group_name

  ecr_repository_url = module.ecr.repository_url


  subnet_id = module.network.public_subnet_id

  security_group_id = module.security.security_group_id
}
