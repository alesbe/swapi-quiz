# Build stage
FROM node:20-alpine AS build
WORKDIR /app

# Copy package.json and install dependencies first (optimized cache)
COPY package*.json ./
RUN npm install

# Copy the rest of the code
COPY . .

# Build for production
RUN npm run build -- --configuration production

# Production stage with NGINX
FROM nginx:stable-alpine

# Copy compiled files
COPY --from=build /app/dist/frontend/browser/ /usr/share/nginx/html/

# Replace NGINX configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 (Docker Compose will map it to 6969)
EXPOSE 80

# Default command (already set by NGINX, but for clarity)
CMD ["nginx", "-g", "daemon off;"]